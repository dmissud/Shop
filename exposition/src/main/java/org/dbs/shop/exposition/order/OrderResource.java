package org.dbs.shop.exposition.order;

import lombok.extern.slf4j.Slf4j;
import org.dbs.shop.application.order.IGetOrderOfCustomerQuery;
import org.dbs.shop.application.order.IOrderCreateUseCase;
import org.dbs.shop.application.order.IOrderCreateUseCase.PlaceItemCommand;
import org.dbs.shop.application.order.IOrderCreateUseCase.PlaceOrderCommand;
import org.dbs.shop.domain.shop.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderResource {

    @Autowired
    private IOrderCreateUseCase orderManagement;

    @Autowired
    private IGetOrderOfCustomerQuery getOrderOfCustomerQuery;

    @Autowired
    private OrderDtoMapper orderMapper;

    @Autowired
    private ItemDtoMapper itemMapper;

    @PostMapping(value = "/customers/{customerName}/orders", produces = { "application/json" })
    public ResponseEntity<URI> createOrder(@PathVariable("customerName") final String customerName,
                                           @Valid @RequestBody final List<PlaceItemCommand> itemDtos) {

        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(customerName, itemDtos);
        final Order order = orderManagement.placeOrder(placeOrderCommand);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getNumber()).toUri();
        log.debug("Location : "+location);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/customers/{customerName}/orders", produces = { "application/json" })
    public List<OrderDTO> listCustomerOrders(@PathVariable("customerName") final String customerName) {

        final List<Order> orders = getOrderOfCustomerQuery.getOrderOfCustomer(customerName);
        return orderMapper.mapToDtoList(orders);
    }

    @GetMapping(value = "/orders/{orderNumber}", produces = { "application/json" })
    public OrderDTO retrieveCustomerOrderByNumber(@PathVariable("orderNumber") final int orderNumber ) {
        final Order order = getOrderOfCustomerQuery.getOrderOfCustomerByNumber(orderNumber);

        return orderMapper.mapToDto(order);
    }

    @GetMapping(value = "/orders", produces = { "application/json" })
    public List<OrderDTO> retrieveCustomerOrderByNumber() {
        final List<Order> orders = getOrderOfCustomerQuery.getAllOrders();

        return orderMapper.mapToDtoList(orders);
    }
}