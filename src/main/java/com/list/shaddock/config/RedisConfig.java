package com.list.shaddock.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.PropertySource;

@Configurable
@PropertySource("classpath:redis.properties")
public class RedisConfig {

}
