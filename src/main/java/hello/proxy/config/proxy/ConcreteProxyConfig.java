package hello.proxy.config.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConcreteProxyConfig {
	
	private final LogTrace logTrace;
	
	@Bean
	public OrderControllerV2 orderControllerV2() {
		OrderControllerV2 controllerV2 = new OrderControllerV2(orderServiceV2());
		return new OrderControllerConcreteProxy(controllerV2, logTrace);
	}

	@Bean
	public OrderServiceV2 orderServiceV2() {
		OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2());
		return new OrderServiceConcreteProxy(orderServiceV2, logTrace);
	}
	
	@Bean
	public OrderRepositoryV2 orderRepositoryV2() {
		OrderRepositoryV2 concreteProxy = new OrderRepositoryV2();
		return new OrderRepositoryConcreteProxy(concreteProxy, logTrace);
	}
}
