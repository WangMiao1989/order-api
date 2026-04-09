package com.wm.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "whitelist")
public class WhitelistProperties {
	private Map<String, List<String>> whitelists = new HashMap<>();
}
