package org.dbs.shop.exposition.order;

import org.apache.commons.lang3.NotImplementedException;
import org.dbs.shop.common.AbstractMapper;
import org.dbs.shop.domain.shop.Order;
import org.dbs.shop.exposition.customer.CustomerDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper extends AbstractMapper<OrderDTO, Order> {

    @Autowired
    private ItemDtoMapper itemMapper;

    @Autowired
    private CustomerDtoMapper customerDtoMapper;

    @Override
    public OrderDTO mapToDto(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setNumber(order.getNumber());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setShipmentDate(order.getShipmentDate());
        orderDTO.setTotal(order.getTotalAmount());
        orderDTO.setItems(itemMapper.mapToDtoList(order.getItems()));
        orderDTO.setCustomer(customerDtoMapper.mapToDto(order.getCustomer()));
        return orderDTO;
    }

}