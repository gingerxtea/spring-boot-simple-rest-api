package me.yassu.restapi.utility.mapper;

import me.yassu.restapi.api.dto.CustomerDto;
import me.yassu.restapi.entity.CustomerEntity;

public final class CustomerMapper {

	private CustomerMapper() {
		// Utility class
	}

	public static CustomerDto toDto(CustomerEntity entity) {
		return new CustomerDto(
				entity.getId(),
				entity.getFirstName(),
				entity.getLastName(),
				entity.getCompany(),
				entity.getCity(),
				entity.getCountry(),
				entity.getPhone1(),
				entity.getPhone2(),
				entity.getEmail(),
				entity.getSubscriptionDate(),
				entity.getWebsiteUrl()
		);
	}
}
