package hello.proxy.app.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 스프링 3.0 이후 부터는 오직 @Controller (3.0 이하에서는 @RequestMapping 도 가능) 이 있어야 스프링 컨트롤러로 인식한다.
@ResponseBody
public interface OrderControllerV1 {

	@GetMapping("/v1/request")
	String request(@RequestParam("itemId") String itemId);
	
	@GetMapping("/v1/no-log")
	String noLog();
}
