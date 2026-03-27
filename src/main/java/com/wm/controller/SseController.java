package com.wm.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wm.utils.ContextHolder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/sse")
public class SseController {

    // 线程安全的列表，保存所有客户端连接
    private final Map<String, List<SseEmitter>> connections = new ConcurrentHashMap<>();
    
    /**
     * SSE 连接端点
     */
    @RequestMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamOrders() {
        SseEmitter emitter = new SseEmitter(0L); // 0表示永不超时
        // 加入emitter
        this.addConnection(ContextHolder.getTenantId(), emitter);
        return emitter;
    }
    
    // 推送消息
    public void sendEmitter(String tenantId, String emitterName, String data) {
    	// 获取当前tenant 的 emitters
    	List<SseEmitter> emitters = connections.get(tenantId);
    	// emitters不存在的情况，结束处理
    	if(Objects.isNull(emitters) || emitters.isEmpty()) {
    		return;
    	}
    	// emitter发送消息
    	for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name(emitterName)
                        .data(data));
            } catch (IOException e) {
            	this.removeConnection(tenantId, emitter);
            }
        }
    }
    
    // 添加connection
    private void addConnection(String tenantId, SseEmitter emitter) {
    	// 如果map中不存在，创建CopyOnWriteArrayList，然后追加emitter
    	connections.computeIfAbsent(tenantId, k -> new CopyOnWriteArrayList<>()).add(emitter);
    	// 成功，timeout，error的回调，删除emitter
    	emitter.onCompletion(() -> this.removeConnection(tenantId, emitter));
        emitter.onTimeout(() -> this.removeConnection(tenantId, emitter));
        emitter.onError((e) -> this.removeConnection(tenantId, emitter));
    }

    // 删除connection
    private void removeConnection(String tenantId, SseEmitter emitter) {
    	// 获取当前tenant 的 emitters
    	List<SseEmitter> emitters = connections.get(tenantId);
    	// 如果emitters存在的情况，删除当前emitter
    	if(!Objects.isNull(emitters)) {
    		emitters.remove(emitter);
    		// 删除之后，emitters为空的情况，从map中删除
    		if(emitters.isEmpty()) {
    			connections.remove(tenantId);
    		}
    	}
    }
}