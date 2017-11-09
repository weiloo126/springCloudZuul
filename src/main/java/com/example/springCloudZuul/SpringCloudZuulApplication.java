package com.example.springCloudZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.springCloudZuul.filter.AccessFilter;
import com.example.springCloudZuul.filter.ErrorFilter;
import com.example.springCloudZuul.filter.ThrowExceptionFilter;

@SpringBootApplication
@EnableZuulProxy //支持网关路由
public class SpringCloudZuulApplication {
	
	/**
	 * 自定义过滤器之后，还需要实例化该过滤器才能生效
	 */
//	@Bean
//	public AccessFilter accessFilter() {
//		return new AccessFilter();
//	}
	
//	@Bean
//	public ThrowExceptionFilter throwExceptionFilter() {
//		return new ThrowExceptionFilter();
//	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulApplication.class, args);
	}
}
