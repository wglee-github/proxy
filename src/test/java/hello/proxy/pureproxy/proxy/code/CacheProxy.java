package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{

	private Subject target; // 실제 데이터에 접근하기 위해 선언
	private String cacheValue; // 캐시를 담아둘 변순
	
	public CacheProxy(Subject target) {
		this.target = target;
	}

	@Override
	public String operation() {
		log.info("프록시 호출");
		if(cacheValue == null)
			cacheValue = target.operation();
		
		return cacheValue;
	}

}
