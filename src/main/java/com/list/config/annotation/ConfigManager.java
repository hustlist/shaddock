package com.list.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ConfigManager
 * @Description 配置注解
 * @Author cughu
 * @Date 2019/9/29 23:04
 * @Version v1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigManager {

    String code() default "";





}
