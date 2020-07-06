package org.dbs.shop.exposition.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.common.MapperExceptionCode;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.user.RoleType;
import org.dbs.shop.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({CustomerRessource.class, MapperExceptionCode.class})
class CustomerRessourceTest {

    private static final String NOM_CUSTOMER = "John Travolta";
    private static final String EMAIL_CUSTOMER = "aBigPassword@gmail.com";
    private static final String NOM_NEW_CUSTOMER = "Ema Thurman";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerManagement customerManagement;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    @BeforeEach
    void setUp() {
        User user = new User(NOM_CUSTOMER, EMAIL_CUSTOMER, RoleType.ROLE_CUSTOMER);
        customer = new Customer();
    }

    @Test
    @DisplayName("Find of a exiting customer")
    void retrieveCustomerByNameShouldSuccess() throws Exception {
        when(customerManagement.retrieveCustomerByName(any(String.class))).thenReturn(customer);
        
        final String result =
                mockMvc.perform(MockMvcRequestBuilders.get("/customers/retrieve/{customerName}", NOM_CUSTOMER).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final CustomerFullDTO customerFullDTO = objectMapper.readValue(result, CustomerFullDTO.class);
        assertThat(customerFullDTO.getPassword()).isEqualTo(EMAIL_CUSTOMER);
    }

    @Test
    void createCustomerShouldSuccess() throws Exception {
        final CustomerDTO customerDTO = new CustomerDTO(NOM_NEW_CUSTOMER);
        mockMvc.perform(MockMvcRequestBuilders.post("/customers/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }
}