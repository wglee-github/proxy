package hello.proxy.config.v2_dynamicproxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DynamicProxyBasicConfig {

	private final LogTrace logTrace;
	
	@Bean
	OrderControllerV1 orderControllerV1() {
		OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1());
		LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);
		OrderControllerV1 proxy = (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[] {OrderControllerV1.class}, handler);
		return proxy;
	}
	
	@Bean
	OrderServiceV1 orderServiceV1() {
		OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1());
		LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);
		OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[] {OrderServiceV1.class}, handler);
		return proxy;
	}
	
	@Bean
	OrderRepositoryV1 orderRepositoryV1() {
		OrderRepositoryV1 target = new OrderRepositoryV1Impl();
		LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);
		OrderRepositoryV1 proxy = (OrderRepositoryV1)Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, handler);
		return proxy;
	}
}
