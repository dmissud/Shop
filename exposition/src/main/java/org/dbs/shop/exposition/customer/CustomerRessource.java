package org.dbs.shop.exposition.customer;

import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRessource {

    private static Logger logger = LoggerFactory.getLogger(CustomerRessource.class);

    @Autowired
    ICustomerManagement customerManagement;

    @Autowired
    CustomerDtoMapper customerDtoMapper;

    @Autowired
    CustomerFullDtoMapper customerFullDtoMapper;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<URI> createCustomer(@NotNull @RequestBody CustomerDTO customerDTO) {
        final Customer customer = customerManagement.referenceCustomer(customerDTO.getCustomerName());
        logger.debug("Customers Create");
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getName()).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping(value = "/customers/{customerName}", produces = {"application/json"})
    public CustomerFullDTO retrieveCustomerBuName(@NotNull @PathVariable("customerName") String customerName) {
        Customer customer = customerManagement.retrieveCustomerByName(customerName);
        logger.debug("Customers Retrieve");

        return customerFullDtoMapper.mapToDto(customer);
    }

    @GetMapping(value = "/customers", produces = {"application/json"})
    public List<CustomerDTO> retrieveCustomers() {

        final List<Customer> customers = customerManagement.retrieveAllCustomers();

        return customerDtoMapper.mapToDtoList(customers);
    }
}
