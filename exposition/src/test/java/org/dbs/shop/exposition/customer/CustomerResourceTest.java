package org.dbs.shop.exposition.customer;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper objectmapper;

	@MockBean
	private ICustomerManagement customerManagement;

	@Test
	public void retrieveCustomerShouldSuccess() throws UnsupportedEncodingException, Exception {
		// Given
		final Customer customer = new Customer("John Doe", "password");
		Mockito.when(customerManagement.retrieveCustomerByName(ArgumentMatchers.any(String.class))).thenReturn(customer);

		// When
		final String result = mockmvc
				.perform(MockMvcRequestBuilders.get("/customers/retrieve/{customerName}", "John Doe")//
						.accept(MediaType.APPLICATION_JSON))//
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("json " + result);
		// Then
		//final CustomerDTO customerDTO = objectmapper.readValue(result, CustomerDTO.class);
		//assertEquals(customerDTO.getCustomerName(), "John Doe");
		assertEquals("{\"customerName\":\"John Doe\",\"pasword\":\"password\"}", result);
	}
}
