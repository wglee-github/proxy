package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TImeDecorator implements Component{

	private Component component;
	
	public TImeDecorator(Component component) {
		this.component = component;
	}
	
	@Override
	public String operation() {
		log.info("TImeDecorator 실행");
		long startTime = System.currentTimeMillis();
		String result = component.operation();
		long endTime = System.currentTimeMillis();
		long resultTime = startTime - endTime;
		log.info("TImeDecorator 종료 resultTime={}ms", resultTime);
		return result;
	}

}
