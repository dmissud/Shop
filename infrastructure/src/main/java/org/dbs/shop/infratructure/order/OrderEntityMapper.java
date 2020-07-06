package org.dbs.shop.infratructure.order;

import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.Order;
import org.dbs.shop.infratructure.common.AbstractMapper;
import org.dbs.shop.infratructure.user.UserEntity;
import org.dbs.shop.infratructure.user.UserEntityMapper;
import org.dbs.shop.infratructure.user.IUserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper extends AbstractMapper<Order, OrderEntity> {

    @Autowired
    private UserEntityMapper customerMapper;

    @Autowired
    private ItemEntityMapper itemMapper;

    @Autowired
    private IUserJpaRepository customerJpaRepository;

    @Override
    public Order mapToDomain(final OrderEntity entity) {
        Order order = new Order(entity.getNumber(),
                entity.getOrderDate(),
                itemMapper.mapToDomainList(entity.getItems()));
        //order.setCustomer(customerMapper.mapToDomain(entity.getCustomer()));
        return order;
    }

    @Override
    public OrderEntity mapToEntity(final Order order) {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setNumber(order.getNumber());
        orderEntity.setCustomer(getCustomerEntity(order.getCustomer()));
        orderEntity.setItems(itemMapper.mapToEntityList(order.getItems()));
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setShipmentDate(order.getShipmentDate());
        return orderEntity;
    }

    private UserEntity getCustomerEntity(final Customer customer) {
        return customerJpaRepository.findByUserName(customer.getName());
    }

}