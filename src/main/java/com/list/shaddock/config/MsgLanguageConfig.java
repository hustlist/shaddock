package com.list.shaddock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="msg")
@PropertySource("classpath:msg_language.properties")
public class MsgLanguageConfig {

	private Map<String,String> lanMap;

	public Map<String, String> getLanMap() {
		return lanMap;
	}

	public void setLanMap(Map<String, String> lanMap) {
		this.lanMap = lanMap;
	}

}
