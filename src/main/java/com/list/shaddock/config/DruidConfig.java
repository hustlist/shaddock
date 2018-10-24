package com.list.shaddock.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
public class DruidConfig {
	
	/**
	 * 注册一个StatViewServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> DruidStatViewServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(),"/druid/*");
		
		/** 白名单*/
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		
		/** 黑名单*/
		servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
		
		/** 用户名*/
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		
		/** 密码*/
		servletRegistrationBean.addInitParameter("loginPassword", "admin@123456");
		
		/** 警用页面的“Reset All”功能*/
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		
		return servletRegistrationBean;
	}
	
	/**
	 * 注册一个WebStatFilter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
		
		/** 过滤规则 */
		filterRegistrationBean.addUrlPatterns("/druid");
		
		/** 忽略资源 */
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
		
		/** 注册bean的顺序*/
		filterRegistrationBean.setOrder(10);
		
		return filterRegistrationBean;
	}

}
