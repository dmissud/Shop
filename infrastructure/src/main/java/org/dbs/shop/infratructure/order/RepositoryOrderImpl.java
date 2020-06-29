package org.dbs.shop.infratructure.order;

import lombok.extern.slf4j.Slf4j;
import org.dbs.shop.domain.IRepositoryOrder;
import org.dbs.shop.domain.Item;
import org.dbs.shop.domain.Order;
import org.dbs.shop.infratructure.customer.ICustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class RepositoryOrderImpl implements IRepositoryOrder {

    public static final int FIST_NUMBER_OF_ORDER = 1000;
    public static final String NOM_CONFIG = "Config 01";

    @Autowired
    private IOrderNumberEntityJpaRepository orderNumberEntityJpaRepository;
    @Autowired
    private IOrderEntityJpaRepository orderEntityJpaRepository;
    @Autowired
    private ICustomerJpaRepository customerJpaRepository;
    @Autowired
    private OrderEntityMapper orderEntityMapper;

    @Override
    public int giveNextOrderNumber() {
        OrderNumberEntity orderNumberEntity = orderNumberEntityJpaRepository.findByName(NOM_CONFIG);
        if (orderNumberEntity != null) {
            orderNumberEntity.setNumberOfLastOrder(orderNumberEntity.getNumberOfLastOrder() + 1);
        } else {
            orderNumberEntity = new OrderNumberEntity();
            orderNumberEntity.setNumberOfLastOrder(FIST_NUMBER_OF_ORDER);
            orderNumberEntity.setName(NOM_CONFIG);
        }
        int numberToUse = orderNumberEntity.getNumberOfLastOrder();

        orderNumberEntity.setLastUpdateDate(LocalDate.now());
        orderNumberEntity.setLastUpdateTime(LocalTime.now());

        orderNumberEntityJpaRepository.save(orderNumberEntity);
        log.debug("Order number : "+numberToUse);

        return numberToUse;
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerJpaRepository.findByUserName(order.getCustomer().getName()));
        orderEntity.setNumber(order.getNumber());
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setShipmentDate(order.getShipmentDate());
        List<Item> items = order.getItems();
        List<ItemEntity> itemEntitys = new ArrayList<>();
        for (Item item : items) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setProductName(item.getProductName());
            itemEntity.setProductPrice(item.getProductPrice());
            itemEntity.setQuantity(item.getQuantity());
            itemEntitys.add(itemEntity);
        }
        orderEntity.setItems(itemEntitys);

        orderEntityJpaRepository.save(orderEntity);
    }

    @Override
    public List<Order> findOrderForCustomer(String customerName) {
        List<OrderEntity> orderEntities = orderEntityJpaRepository.findByCustomerUserName(customerName);
        return orderEntityMapper.mapToDomainList(orderEntities);
    }

    @Override
    public Order findOrderByNumber(int numOrder) {
        return orderEntityMapper.mapToDomain(orderEntityJpaRepository.findByNumber(numOrder));
    }

    @Override
    public List<Order> findAllOrders() {
        List<OrderEntity> orderEntities = orderEntityJpaRepository.findAll();
        return orderEntityMapper.mapToDomainList(orderEntities);
    }
}
