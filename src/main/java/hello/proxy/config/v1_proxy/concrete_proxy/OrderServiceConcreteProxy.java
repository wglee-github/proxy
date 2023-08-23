package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2{

	private final OrderServiceV2 target;
	private final LogTrace logTrace;
	
	/**
	 * 부모 클래스의 기본 생성자 이외의 추가 생성자가 있는 경우 자식 클래스는 해당 생성자를 무조건 호출해줘야 한다.
	 * 여기서는 부모의 생성자를 활용할 이유가 없기 때문에 null로 처리한다.
	 */
	public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
		super(null);
		this.target = target;
		this.logTrace = logTrace;
	}

	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("orderServiceV2.orderItem()");
			target.orderItem(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
	
}
