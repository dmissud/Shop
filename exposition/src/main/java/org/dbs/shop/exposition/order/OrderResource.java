package org.dbs.shop.exposition.order;

import java.util.List;

import javax.validation.Valid;

import org.dbs.shop.application.order.IOrderManagement;
import org.dbs.shop.domain.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderResource {

	private static Logger logger = LoggerFactory.getLogger(OrderResource.class);

	@Autowired
	private IOrderManagement orderManagement;

	@Autowired
	private OrderDtoMapper orderMapper;

	@Autowired
	private ItemDtoMapper itemMapper;

	@PostMapping(value = "customers/{customerName}/orders", produces = { "application/json" })
	public OrderDTO createOrder(@PathVariable("customerName") final String customerName,
			@Valid @RequestBody final List<ItemDTO> itemDtos) {

		final Order order = orderManagement.createOrder(customerName, itemMapper.mapToDomainList(itemDtos));

		return orderMapper.mapToDto(order);
	}

	@GetMapping(value = "customers/{customerName}/orders", produces = { "application/json" })
	public OrderDTO listCustomerOrders(@PathVariable("customerName") final String customerName) {

		final Order order = orderManagement.listCustomerOrders(customerName);

		return orderMapper.mapToDto(order);
	}
}
