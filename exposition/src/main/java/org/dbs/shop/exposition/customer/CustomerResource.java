package org.dbs.shop.exposition.customer;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerResource {

	private static Logger logger = LoggerFactory.getLogger(CustomerResource.class);

	@Autowired
	private ICustomerManagement customerManagement;

	@Autowired
	private CustomerDtoMapper customerMapper;

	@PostMapping("customers")
	@ResponseStatus(HttpStatus.CREATED)
	public void createCustomer(@NotNull @RequestBody final CustomerDTO customerDTO) {
		customerManagement.referenceCustomer(customerDTO.getCustomerName());
	}

	@GetMapping(value = "customers/{customerName}", produces = { "application/json" })
	public ResponseEntity<CustomerFullDTO> retrieveCustomerByName(
			@NotNull @PathVariable("customerName") final String customerName) {

		final Customer customer = customerManagement.retrieveCustomerByName(customerName);
		final CustomerFullDTO customerFullDto = new CustomerFullDTO();
		customerFullDto.setCustomerName(customer.getName());
		customerFullDto.setPassword(customer.getPassword());

		return new ResponseEntity<CustomerFullDTO>(customerFullDto, HttpStatus.OK);
	}

	@GetMapping(value = "customers", produces = { "application/json" })
	public List<CustomerDTO> retrieveCustomers() {

		final List<Customer> customers = customerManagement.listCustomers();

		return customerMapper.mapToDtoList(customers);
	}

}
