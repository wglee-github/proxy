package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3{

	private final OrderRepositoryV3 orderRepository;
	
	public void orderItem(String itemId) {
		orderRepository.save(itemId);
	}

}
