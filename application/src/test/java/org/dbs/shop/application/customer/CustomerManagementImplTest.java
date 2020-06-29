package org.dbs.shop.application.customer;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.dbs.shop.domain.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CustomerManagementImpl.class})
class CustomerManagementImplTest {

    private static final String CUSTOMER_NAME = "John Travolta";
    private static final String CUSTOMER_PASSWORD = "ABigPassword";
    private static final String CUSTOMER_NAME_NOT_EXIST = "j'existe pas";

    @Autowired
    ICustomerManagement customerManagement;

    @MockBean
    IRepositoryCustomer repositoryCustomer;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(CUSTOMER_NAME, CUSTOMER_PASSWORD);
    }

    @Test
    @DisplayName("Create a new Customer and success")
    void should_i_correctly_create_customer_for_a_new_customer() {
        when(repositoryCustomer.findByName(CUSTOMER_NAME)).thenReturn(customer);
        customerManagement.referenceCustomer(CUSTOMER_NAME);
    }

    @Test
    @DisplayName("Retrieve a new Customer and success")
    void retrieveCustomerByName() {
        when(repositoryCustomer.findByName(any(String.class))).thenReturn(customer);
        Customer customerSearch = customerManagement.retrieveCustomerByName(CUSTOMER_NAME);

        assertThat(customerSearch.getPassword()).isEqualTo(CUSTOMER_PASSWORD);
    }

    @Test
    @DisplayName("Retrieve a new Customer that don't exist")
    void should_i_have_a_exception_when_i_search_a_non_existing_customer() {
        when(repositoryCustomer.findByName(any(String.class))).thenThrow(new NotFoundException("Customer Not Found : "+ CUSTOMER_NAME_NOT_EXIST));
        Throwable thrown = catchThrowable(() -> {
            Customer customerSearch = customerManagement.retrieveCustomerByName(CUSTOMER_NAME_NOT_EXIST);
        });
        assertThat(thrown).isNotNull();
        assertThat(thrown).as("Try find a non existing customer").hasMessage("Customer Not Found : "+ CUSTOMER_NAME_NOT_EXIST);

    }

}