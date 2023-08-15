package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{

	private ConcreteLogic concreteLogic;
	
	public TimeProxy(ConcreteLogic concreteLogic) {
		this.concreteLogic = concreteLogic;
	}
	
	/**
	 * super.operation() 와 concreteLogic.operation() 의 차이
	 * 즉, 직접 호출하는 경우와 의존성을 주입받는 경우의 장,단점
	 * 1. 직접 호출하는 경우
	 * 장점
	 * - 단순함 : super를 사용하는 경우 별도의 멤버 변수나 생성자가 필요하지 않는다. 
	 * - 직관성 : 해당 프록시 클래스가 부모 클래스의 특정 메서드를 오버라이드하는 것이 명확하게 들어난다.
	 * 단점
	 * - 강한결합 : TimeProxy가 ConcreteLogic에 강하게 결합되어 TimeProxy의 활용 범위가 ConcreteLogic에 국한된다.
	 * - 확장의 제한 : 다른 로직의 프록시로 사용하려면 새로운 프록시 클래스를 만들어야 한다.
	 * 
	 * 2. 주입받는 경우
	 * - 유연성 : 동일한 TimeProxy 클래스를 여러 ConcreteLogic의 하위 클래스에 대해 사용할 수 있다. 
	 * 	 즉, 다양한 ConcreteLogic의 서브 클래스들을 주입 받아 동일한 프록시 로직을 적용할 수 있다.
	 * - 테스트 용이성 : 테스트 시 mock 객체나 다른 구현체를 주입하여 테스트하기 용이하다
	 * 
	 * 결국 인터페이스 아니라 상속 관계에서도 주입을 통해 접근하는 방식은 프록시 클래스의 활용 범위와 재사용성을 높이기 때문에 더 우수한 방식인것 같다.
	 */
	@Override
	public String operation() {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();
		String result = concreteLogic.operation();
		long endTime = System.currentTimeMillis();
		long resultTime = startTime - endTime;
		log.info("TimeProxy 종료 resultTime={}ms", resultTime);
		return result;
	}

}
