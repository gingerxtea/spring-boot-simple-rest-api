package me.yassu.restapi.api.conroller;

import me.yassu.restapi.api.dto.OrderDto;
import me.yassu.restapi.repository.OrderRepository;
import me.yassu.restapi.utility.mapper.OrderMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

	private final OrderRepository orderRepository;

	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("/orders")
	ResponseEntity<List<OrderDto>> getAllOrders(@RequestParam("size") Long size) {
		return ResponseEntity.ok(orderRepository
				.findAll(Pageable.ofSize(Math.toIntExact(size))).stream().map(OrderMapper::toDto).toList());
	}
}
