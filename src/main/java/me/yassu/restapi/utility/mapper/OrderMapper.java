package me.yassu.restapi.utility.mapper;

import me.yassu.restapi.api.dto.OrderDto;
import me.yassu.restapi.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public final class OrderMapper {

	private OrderMapper() {
		/* This utility class should not be instantiated */
	}

	public static OrderDto toDto(OrderEntity entity) {
		if (entity == null) {
			return null;
		}

		return new OrderDto(
				entity.getId(),
				entity.getCustomerId(),
				entity.getOrderDate(),
				entity.getStatus(),
				entity.getTotalAmount(),
				entity.getShippingAmount(),
				entity.getPaymentMethod(),
				entity.getShippingCity(),
				entity.getCreatedAt(),
				entity.getUpdatedAt()
		);
	}
}
