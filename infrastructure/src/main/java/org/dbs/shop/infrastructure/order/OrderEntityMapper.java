package org.dbs.shop.infrastructure.order;

import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.order.Order;
import org.dbs.shop.infrastructure.AbstractMapper;
import org.dbs.shop.infrastructure.customer.CustomerEntity;
import org.dbs.shop.infrastructure.customer.CustomerEntityMapper;
import org.dbs.shop.infrastructure.customer.ICustomerJpaRepository;
import org.dbs.shop.infrastructure.item.ItemEntity;
import org.dbs.shop.infrastructure.item.ItemEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper extends AbstractMapper<Order, OrderEntity> {

	@Autowired
	private CustomerEntityMapper customerMapper;

	@Autowired
	private ItemEntityMapper itemMapper;

	@Autowired
	private ICustomerJpaRepository customerJpaRepository;

	@Override
	public Order mapToDomain(final OrderEntity entity) {
		final Order order = new Order(customerMapper.mapToDomain(entity.getCustomer()), entity.getNumber(),
				entity.getOrderDate());
		for (final ItemEntity itemEntity : entity.getItems()) {
			order.addItem(itemMapper.mapToDomain(itemEntity));
		}
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

	private CustomerEntity getCustomerEntity(final Customer customer) {
		return customerJpaRepository.findByUserName(customer.getName());
	}

}