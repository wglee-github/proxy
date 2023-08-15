package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import hello.proxy.config.proxy.ConcreteProxyConfig;
import hello.proxy.config.proxy.InterfaceProxyConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;

//@Import({AppV1Config.class, AppV2Config.class})
@Import({InterfaceProxyConfig.class, ConcreteProxyConfig.class})
// 컴포넌트 스캔도 되고, 빈도 수동으로 직접 등록하게 되면 스프링 컨테이너에 등록시 충돌 오류가 발생한다. 주의하여 scanBasePackages 를 적용하자.
@SpringBootApplication(scanBasePackages = "hello.proxy.app.v3") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
