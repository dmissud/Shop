package org.dbs.shop.infratructure;

import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.user.IRepositoryUser;
import org.dbs.shop.domain.user.RoleType;
import org.dbs.shop.domain.user.User;
import org.dbs.shop.infratructure.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("Validation of the repository of Customers")
class RepositoryUserImplTest {

    public static final String NOM_CUSTOMER = "Bob Lincoln";
    public static final String EMAIL = "aPassword@gmail.com";
    public static final String NOM_NEW_CUSTOMER = "Bob leponge";
    public static final String EMAIL_NEW_CUSTOMER = "newPassword@gmail.com";
    public static final String OTHER_NOM_CUSTOMER = "Je n'existe pas";

    @Autowired
    private IRepositoryUser repositoryUser;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(NOM_CUSTOMER);
        userEntity.setEmail(EMAIL);
        entityManager.persist(userEntity);
    }


    @Test
    @DisplayName("Save a new customers")
    void should_create_a_customer_when_give_new_customer() {
        User customerBobLincoln = new User(NOM_NEW_CUSTOMER, EMAIL_NEW_CUSTOMER, RoleType.ROLE_CUSTOMER);
        repositoryUser.save(customerBobLincoln);

        List lstCust = entityManager.getEntityManager().createQuery("select c from UserEntity c where c.userName= :nomAttenduDansQuery")
                .setParameter("nomAttenduDansQuery", NOM_NEW_CUSTOMER)
                .getResultList();
        assertThat(lstCust.size()).isEqualTo(1);
        assertThat(((UserEntity)lstCust.get(0)).getEmail()).isEqualTo(EMAIL_NEW_CUSTOMER);
    }

    @Test
    @DisplayName("Find a existing customer")
    void should_find_a_customer_when_search_a_exiting_customer() {
        User user = repositoryUser.findByName((NOM_CUSTOMER));
        assertThat(user.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @DisplayName("Catch Exception when try to Find a non existing customer")
    void should_have_exception_when_search_a_not_exiting_customer() {
        Throwable thrown = catchThrowable(() -> {
            User user = repositoryUser.findByName((OTHER_NOM_CUSTOMER));
        });
        assertThat(thrown).as("Try find a non existing customer").hasMessage("Customer Not Found : "+OTHER_NOM_CUSTOMER);
    }

    @Test
    @DisplayName("Try to Save a customers that allready exist and catch a exception")
    void should_create_gave_a_exception_when_give_customer_All_ready_exist() {
        Throwable thrown = catchThrowable(() -> repositoryUser.save(new User(NOM_CUSTOMER, EMAIL, RoleType.ROLE_CUSTOMER)));
        assertThat(thrown).as("Try create a existing customer").hasMessage("Customer All Ready Exist : "+NOM_CUSTOMER);
    }

}