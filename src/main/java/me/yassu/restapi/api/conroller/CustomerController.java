package me.yassu.restapi.api.conroller;

import me.yassu.restapi.api.dto.CustomerDto;
import me.yassu.restapi.repository.CustomerRepository;
import me.yassu.restapi.utility.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers(@RequestParam("size") Long size) throws ExecutionException, InterruptedException {

		CompletableFuture<List<CustomerDto>> completableFuture = CompletableFuture.supplyAsync(() -> {
			logger.info("current thread: {}", Thread.currentThread().getName());
			List<CustomerDto> dtoListt = customerRepository
					.findBetweenId(1L, size).stream().map(CustomerMapper::toDto).toList();
			logger.info("customer size: {}", dtoListt.size());
			return dtoListt;
		}) ;

		CompletableFuture<List<CustomerDto>> completableFuture01 = CompletableFuture.supplyAsync(() -> {
			logger.info("current thread 01: {}", Thread.currentThread().getName());
			List<CustomerDto> dtoList01 = customerRepository
					.findBetweenId(size + 1L, 100000L).stream().map(CustomerMapper::toDto).toList();
			logger.info("customer size 01: {}", dtoList01.size());
			return dtoList01;
		}) ;

		logger.info("current thread before get(): {}", Thread.currentThread().getName());
		List<CustomerDto> data01 = completableFuture.get();
		List<CustomerDto> data02 = completableFuture01.get();
		List<CustomerDto> data03 = new ArrayList<>();
		data03.addAll(data01);
		data03.addAll(data02);
		logger.info("current thread after get(): {}", Thread.currentThread().getName());


		logger.info("total size: {}", data01.size());
		logger.info("========================================================");
		return ResponseEntity.ok(data03);
	}
}
