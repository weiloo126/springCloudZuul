package com.example.springCloudZuul.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 在请求生命周期的pre、route、post三个阶段中有异常抛出的时候都会进入error阶段的处理，所以我们可以通过创建一个error类型的过滤器来捕获这些异常信息，
 * 并根据这些异常信息在请求上下文中注入需要返回给客户端的错误描述，这里我们可以直接沿用在try-catch处理异常信息时用的那些error参数，这样就可以让这些信息被SendErrorFilter捕获并组织成消息响应返回给客户端
 * 
 *   
 * @date 2017年10月31日
 */
public class ErrorFilter extends ZuulFilter {
	
	private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		log.error("this is a ErrorFilter: {}", throwable.getCause().getMessage());
		ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception", throwable.getCause());
		return null;
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

}
