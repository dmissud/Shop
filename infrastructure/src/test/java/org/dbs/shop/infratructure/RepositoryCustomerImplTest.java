package org.dbs.shop.infratructure;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class RepositoryCustomerImplTest {

    public static final String NOM_CUSTOMER = "Bob Lincoln";
    public static final String PASSWORD = "aPassword";
    public static final String NOM_NEW_CUSTOMER = "Bob leponge";
    public static final String PASSWORD_NEW_CUSTOMER = "newPassword";
    public static final String OTHER_NOM_CUSTOMER = "Je n'existe pas";

    @Autowired
    private IRepositoryCustomer repositoryCustomer;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUserName(NOM_CUSTOMER);
        customerEntity.setPassword(PASSWORD);
        entityManager.persist(customerEntity);
    }


    @Test
    @DisplayName("Save a new customers")
    void should_create_a_customer_when_give_new_customer() {
        Customer customerBobLincoln = new Customer(NOM_NEW_CUSTOMER, PASSWORD_NEW_CUSTOMER);
        repositoryCustomer.save(customerBobLincoln);

        List lstCust = entityManager.getEntityManager().createQuery("select c from CustomerEntity c where c.userName= :nomAttenduDansQuery")
                .setParameter("nomAttenduDansQuery", NOM_NEW_CUSTOMER)
                .getResultList();
        assertThat(lstCust.size()).isEqualTo(1);
        assertThat(((CustomerEntity)lstCust.get(0)).getPassword()).isEqualTo(PASSWORD_NEW_CUSTOMER);
    }

    @Test
    @DisplayName("Find a existing customer")
    void should_find_a_customer_when_search_a_exiting_customer() {
        Customer customer = repositoryCustomer.findByName((NOM_CUSTOMER));
        assertThat(customer.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    @DisplayName("Catch Exception when try to Find a non existing customer")
    void should_have_exception_when_search_a_not_exiting_customer() {
        Throwable thrown = catchThrowable(() -> {
            Customer customer = repositoryCustomer.findByName((OTHER_NOM_CUSTOMER));
        });
        assertThat(thrown).as("Try find a non existing customer").hasMessage("Customer Not Found : "+OTHER_NOM_CUSTOMER);

    }

}