package com.example.springCloudZuul.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

/**
 * @author LW  
 * @date 2017年10月31日
 */
public class ThrowExceptionFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.info("This is a pre filter, it will throw a RuntimeException");
		doSomething();
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
}
