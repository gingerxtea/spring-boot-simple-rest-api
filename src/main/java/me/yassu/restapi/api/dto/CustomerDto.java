package me.yassu.restapi.api.dto;

public record CustomerDto(Long id,
						String firstName,
						String lastName,
						String company,
						String city,
						String country,
						String phone1,
						String phone2,
						String email,
						String subscriptionDate,
						String websiteUrl
) {
}
