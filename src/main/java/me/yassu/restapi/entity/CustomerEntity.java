package me.yassu.restapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "company")
	private String company;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "phone_1")
	private String phone1;

	@Column(name = "phone_2")
	private String phone2;

	@Column(name = "email")
	private String email;

	@Column(name = "subscription_date")
	private String subscriptionDate;

	@Column(name = "website_url")
	private String websiteUrl;
}
