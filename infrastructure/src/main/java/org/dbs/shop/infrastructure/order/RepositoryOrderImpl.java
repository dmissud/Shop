package org.dbs.shop.infrastructure.order;

import java.util.List;

import org.dbs.shop.domain.order.IRepositoryOrder;
import org.dbs.shop.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryOrderImpl implements IRepositoryOrder {

	@Autowired
	private IRepositoryJpaOrder orderJpaRepository;

	@Autowired
	private OrderEntityMapper mapper;

	@Override
	public Order save(final Order order) {
		orderJpaRepository.save(mapper.mapToEntity(order));
		return order;
	}

	@Override
	public List<Order> getCustomerOrders(final String customerName) {
		return mapper.mapToDomainList(orderJpaRepository.findByCustomerUserName(customerName));
	}

}
