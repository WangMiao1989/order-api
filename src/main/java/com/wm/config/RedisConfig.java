package com.wm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.wm.entity.CartDishEntity;

@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<String, CartDishEntity > cartRedisTemplate(RedisConnectionFactory factory){
		// 创建RedisTemplate实例，指定键类型为String，值类型为CartDishEntity
		RedisTemplate<String, CartDishEntity> template = new RedisTemplate<>();
		// 设置连接工厂，用于创建Redis连接
		template.setConnectionFactory(factory);
		// 创建Jackson2JsonRedisSerializer序列化器，专门用于CartDishEntity类型
		Jackson2JsonRedisSerializer<CartDishEntity> serializer = new Jackson2JsonRedisSerializer<>(CartDishEntity.class);
	
		template.setDefaultSerializer(serializer);
		// 设置键（Key）的序列化器，通常使用StringRedisSerializer，使键可读
        template.setKeySerializer(new StringRedisSerializer());
        // 设置哈希键（Hash Key）的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置哈希表值（Hash Value）的序列化器（如果使用hash结构）
     	template.setHashValueSerializer(serializer);
        // 初始化模板，应用上述配置
        template.afterPropertiesSet();
		return template;
	}
}
