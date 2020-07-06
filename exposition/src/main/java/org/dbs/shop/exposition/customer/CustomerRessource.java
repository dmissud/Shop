package org.dbs.shop.exposition.customer;

import lombok.extern.slf4j.Slf4j;
import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.user.User;
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
@Slf4j
public class CustomerRessource {

    @Autowired
    ICustomerManagement customerManagement;

    @Autowired
    CustomerDtoMapper customerDtoMapper;

    @Autowired
    CustomerFullDtoMapper customerFullDtoMapper;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<URI> createCustomer(@NotNull @RequestBody CustomerDTO customerDTO) {
        final Customer customer = customerManagement.referenceCustomer(customerDTO.getCustomerName(), customerDTO.getEmail());
        log.debug("Customers Create");
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getNumber()).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping(value = "/customers/{customerName}", produces = {"application/json"})
    public CustomerFullDTO retrieveCustomerBuName(@NotNull @PathVariable("customerName") String customerName) {
        Customer customer = customerManagement.retrieveCustomerByName(customerName);
        log.debug("Customers Retrieve");

        return customerFullDtoMapper.mapToDto(customer);
    }

    @GetMapping(value = "/customers", produces = {"application/json"})
    public List<CustomerDTO> retrieveCustomers() {

        final List<Customer> customers = customerManagement.retrieveAllCustomers();

        return customerDtoMapper.mapToDtoList(customers);
    }
}
