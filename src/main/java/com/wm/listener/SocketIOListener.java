package com.wm.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.wm.entity.CartDishEntity;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocketIOListener {
	
	@Autowired
	private SocketIOServer server;
	
	@Autowired
	private RedisTemplate<String, CartDishEntity> redisTemplate;

	@PostConstruct
	public void init() {
		// 创建连接监听
		server.addConnectListener(client -> {
			String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
			client.set("roomId", roomId);
			// 加入房间
			client.joinRoom(roomId);
			// 广播在线人数
			sendOnlineCount(client);
			// 广播购物车全部信息
			sendCartList(roomId);
		});
		
		// 断开连接监听
		server.addDisconnectListener(client -> {
			String roomId = client.get("roomId");
			if (!Objects.isNull(roomId)) {
				// 广播在线人数
				sendOnlineCount(client);
		    }
		});
		
		// 购物车更新监听
		server.addEventListener("cart-updated", CartDishEntity.class, (client, data, ackSender) ->  {
			String roomId = client.get("roomId");
			// 保存购物车信息到redis，并广播购物车信息
			saveCart(roomId, data);
			// 广播弹幕信息
			sendBulletScreen(roomId, data);
		});
		
		// 清空购物车
		server.addEventListener("cart-clear", Object.class, (client, data, ackSender) ->  {
			String roomId = client.get("roomId");
			// 清空redis
			redisTemplate.delete("cart:" + roomId);
			// 广播购物车全部信息
			sendEvent(roomId, "cart-change", new ArrayList<>());
			// 广播更新信息
			sendEvent(roomId, "bullet-screen-add", "订单已提交");
		});
	}
	
	// 保存购物车信息到redis
	private void saveCart(String roomId, CartDishEntity data) {
		// 购物车key
		String cartKey = "cart:" + roomId;
		// 菜品key
		String dishKey = String.valueOf(data.getDishId());
		// 购物车信息存入redis
		HashOperations<String, String, CartDishEntity> redisHash= redisTemplate.opsForHash();
		// 通过购物车key和菜品key，取出菜品信息
		CartDishEntity dish = redisHash.get(cartKey, dishKey);
		if(Objects.isNull(dish) && data.getQuantity() > 0) {
			// 菜品不存在&&件数大于0的情况，追加菜品
			redisHash.put(cartKey, dishKey, data);
		} else {
			Integer newQuantity = dish.getQuantity() + data.getQuantity();
			if(newQuantity > 0) {
				// 加算完，如果大于0的情况，保存购物车信息
				dish.setQuantity(newQuantity);
				redisHash.put(cartKey, dishKey, dish);
			} else {
				// 加算完，等于0的情况，删除该菜品
				redisHash.delete(cartKey, dishKey);
			}
		}
		// 设置过期时间30分钟，每次更新
		redisTemplate.expire(cartKey, 30, TimeUnit.MINUTES);
		// 广播购物车全部信息
		List<CartDishEntity> cartList = redisHash.values(cartKey);
		sendEvent(roomId, "cart-change", cartList);
	}
	
	// 广播购物车信息
	private void sendCartList(String roomId) {
		// 购物车key
		String cartKey = "cart:" + roomId;
		// redis
		HashOperations<String, String, CartDishEntity> redisHash= redisTemplate.opsForHash();
		List<CartDishEntity> cartList = redisHash.values(cartKey);
		// 广播购物车全部信息
		sendEvent(roomId, "cart-change", cartList);
	}
	
	// 广播在线人数
	private void sendOnlineCount(SocketIOClient client) {
		String roomId = client.get("roomId");
		int customerCnt = client.getCurrentRoomSize(roomId);
		sendEvent(roomId, "online-count", customerCnt);
	}
	
	// 广播弹幕信息
	private void sendBulletScreen(String roomId, CartDishEntity data) {
		String notice = data.getName() + " " + (data.getQuantity() > 0 ? "+" : "") + data.getQuantity();
		sendEvent(roomId, "bullet-screen-add", notice);
	}
	
	// 广播信息
	private void sendEvent(String roomId, String EventName,Object data) {
		server.getRoomOperations(roomId).sendEvent(EventName, data);
	}
}
