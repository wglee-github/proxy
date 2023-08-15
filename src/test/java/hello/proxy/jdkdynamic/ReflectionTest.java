package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionTest {

//	@Test
	void reflection0() {
		
		Hello target = new Hello();
		
		log.info("start");
		String result1 = target.callA();
		log.info("result1={}", result1);
		
		log.info("start");
		String result2 = target.callB();
		log.info("result2={}", result2);
		
	}
	
//	@Test
	void reflection1() throws Exception {
		
		Hello target = new Hello();
		
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		
		log.info("start");
		Method methodCallA = classHello.getMethod("callA");
		Object result1 = methodCallA.invoke(target);
		log.info("result1={}", result1);
		
		Method methodCallB = classHello.getMethod("callB");
		Object result2 = methodCallB.invoke(target);
		log.info("result2={}", result2);
	}
	
	@Test
	void reflection2() throws Exception {
		
		Hello target = new Hello();
		
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		
		Method methodCallA = classHello.getMethod("callA");
		dynamicCall(methodCallA, target);
		
		Method methodCallB = classHello.getMethod("callB");
		dynamicCall(methodCallB, target);
	}
	
	private void dynamicCall(Method method, Object target) throws Exception {
		log.info("start");
		Object result = method.invoke(target);
		log.info("result={}", result);
	}
	
	
	static class Hello{
		public String callA() {
			log.info("callA");
			return "A";
		}
		public String callB() {
			log.info("callB");
			return "A";
		}
	}
}
