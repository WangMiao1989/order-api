package com.wm.config;

import org.springframework.context.annotation.Bean;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
	@Bean(initMethod = "start", destroyMethod = "stop")
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(9092);
        config.setOrigin("https://www.online-ordering.site,http://49.232.151.8");
        return new SocketIOServer(config);
    }
}
