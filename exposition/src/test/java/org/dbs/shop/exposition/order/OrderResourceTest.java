package org.dbs.shop.exposition.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dbs.shop.application.order.IOrderManagement;
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
@WebMvcTest(OrderResource.class)
public class OrderResourceTest {

	private static final String CUSTOMER_NAME = "John Doe";

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper objectmapper;

	@MockBean
	private IOrderManagement orderManagement;

	@MockBean
	private OrderDtoMapper orderDtoMapper;

	@MockBean
	private ItemDtoMapper itemDtoMapper;

	@Test
	@WithMockUser(roles = { "USER" }) // Given
	public void simpleUserReferencingOrderShouldSuccess() throws UnsupportedEncodingException, Exception {
		// When
		final List<ItemDTO> request = new ArrayList<>();
		request.add(new ItemDTO("apple", BigDecimal.valueOf(1.6), 2));
		request.add(new ItemDTO("lemon", BigDecimal.valueOf(0.9), 2));
		mockmvc.perform(post("/api/customers/{customerName}/orders", CUSTOMER_NAME)//
				.content(objectmapper.writeValueAsString(request)) //
				.contentType(MediaType.APPLICATION_JSON))
		// Then
		.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles = { "ADMIN" }) // Given
	public void adminUserReferencingOrderShouldFail() throws Exception {
		// When
		final List<ItemDTO> request = new ArrayList<>();
		mockmvc.perform(post("/api/customers/{customerName}/orders", CUSTOMER_NAME)//
				.content(objectmapper.writeValueAsString(request)) //
				.contentType(MediaType.APPLICATION_JSON))
		// Then
		.andExpect(status().isForbidden());
	}

}
