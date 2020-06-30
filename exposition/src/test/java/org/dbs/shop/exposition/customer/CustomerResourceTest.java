package org.dbs.shop.exposition.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.customer.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {

	private static final String CUSTOMER_NAME = "John Doe";

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper objectmapper;

	@MockBean
	private ICustomerManagement customerManagement;

	@MockBean
	private CustomerDtoMapper customerMapper;

	@Test
	@WithMockUser(roles = { "ADMIN" })
	public void adminUserRetrievingCustomerShouldSuccess() throws UnsupportedEncodingException, Exception {
		// Given
		final Customer customer = new Customer(CUSTOMER_NAME, "password");
		when(customerManagement.retrieveCustomerByName(any(String.class))).thenReturn(customer);

		// When
		final String result = mockmvc.perform(get("/api/customers/{customerName}", CUSTOMER_NAME)//
				.accept(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Then
		// final CustomerFullDTO customerFullDTO = objectmapper.readValue(result,
		// CustomerFullDTO.class);
		// assertEquals(customerFullDTO.getCustomerName(), CUSTOMER_NAME);
		assertEquals("{\"customerName\":\"John Doe\",\"password\":\"password\"}", result);
	}

	@Test
	@WithMockUser(roles = { "USER" }) // Given
	public void simpleUserRetrievingCustomerShouldFail() throws UnsupportedEncodingException, Exception {

		// When
		mockmvc.perform(get("/api/customers/{customerName}", CUSTOMER_NAME)//
				.accept(MediaType.APPLICATION_JSON))//
		.andExpect(status().isForbidden()); // then

	}
}
