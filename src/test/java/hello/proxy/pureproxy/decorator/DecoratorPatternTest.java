package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TImeDecorator;

public class DecoratorPatternTest {

//	@Test
	void noDecoratorTest() {
		Component component = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(component);
		client.execute();
	}
	
//	@Test
	void decorator1() {
		Component realComponent = new RealComponent();
		Component messageDecorator = new MessageDecorator(realComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
		client.execute();
	}
	
	@Test
	void decorator2() {
		Component realComponent = new RealComponent();
		Component messageDecorator = new MessageDecorator(realComponent);
		Component timeDocorator = new TImeDecorator(messageDecorator);
		DecoratorPatternClient client = new DecoratorPatternClient(timeDocorator);
		client.execute();
	}
}
