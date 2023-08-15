package hello.proxy.config.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InterfaceProxyConfig {

	private final LogTrace logTrace;
	
	@Bean
	public OrderControllerV1 orderControllerV1() {
		return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderServiceV1()), logTrace);
	}
	
	@Bean
	public OrderServiceV1 orderServiceV1() {
		return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepositoryV1()), logTrace);
	}
	
	@Bean
	public OrderRepositoryV1 orderRepositoryV1() {
		return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), logTrace);
	}
}
