package me.yassu.restapi.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderDto(
		Long id,
		Long customerId,
		LocalDate orderDate,
		String status,
		BigDecimal totalAmount,
		BigDecimal shippingAmount,
		String paymentMethod,
		String shippingCity,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
) {
}
