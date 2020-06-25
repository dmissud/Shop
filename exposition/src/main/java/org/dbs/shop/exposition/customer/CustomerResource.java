package org.dbs.shop.exposition.customer;

import javax.validation.constraints.NotNull;

import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	private static Logger logger = LoggerFactory.getLogger(CustomerResource.class);

	@Autowired
	ICustomerManagement customerManagement;

	@PostMapping("create")
	@ResponseStatus(HttpStatus.OK)
	public void createCustomer(@NotNull @RequestBody final CustomerDTO customerDTO) {
		if (customerDTO.getCustomerName() != null) {
			try {
				customerManagement.referenceCustomer(customerDTO.getCustomerName());
			} catch (final CustomerAlreadyExistException e) {
				logger.error(e.getMessage());
			}
		}
	}

	@GetMapping("retrieve/{customerName}")
	public CustomerFullDTO retrieveCustomerBuName(@NotNull @PathVariable("customerName") final String customerName) {
		CustomerFullDTO customerFullDto = null;

		final Customer customer = customerManagement.retrieveCustomerByName(customerName);
		customerFullDto = new CustomerFullDTO();
		customerFullDto.setCustomerName(customer.getName());
		customerFullDto.setPassword(customer.getPassword());

		return customerFullDto;
	}
}
