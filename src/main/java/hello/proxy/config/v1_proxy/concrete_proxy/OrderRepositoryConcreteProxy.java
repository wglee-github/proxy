package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2{

	private OrderRepositoryV2 target;
	private LogTrace logTrace;
	
	public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
		this.target = target;
		this.logTrace = logTrace;
	}

	@Override
	public void save(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderRepositoryV2.save()");
			target.save(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}

}
