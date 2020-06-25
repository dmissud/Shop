package org.dbs.shop.exposition.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dbs.shop.application.customer.ICustomerManagement;
import org.dbs.shop.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ICustomerManagement customerManagement;

	@Autowired
	private ObjectMapper objectMapper;

	private Customer customer;

	@Before
	public void setup() {
		customer = new Customer("John Doe", "password");
	}

	@Test
	public void retrieveCustomerShouldSuccess() throws Exception {

		when(customerManagement.retrieveCustomerByName(any(String.class))).thenReturn(customer);

		final String result = mockMvc
				.perform(MockMvcRequestBuilders.get("/customers/retrieve/{customerName}", "John Doe") //
						.accept(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andReturn().getResponse().getContentAsString();

		final CustomerDTO customerDTO = objectMapper.readValue(result, CustomerDTO.class);
		assertThat(customerDTO.getCustomerName()).isEqualTo("John Doe");
	}

	@Test
	public void createCustomerShouldSuccess() throws Exception {
		final CustomerDTO customerDto = new CustomerDTO("John Doe");
		mockMvc.perform(MockMvcRequestBuilders.post("/customers/create") //
				.accept(MediaType.APPLICATION_JSON) //
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customerDto))) //
		.andExpect(status().isOk());
	}

}
