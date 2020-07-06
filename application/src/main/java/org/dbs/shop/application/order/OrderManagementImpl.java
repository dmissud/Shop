package org.dbs.shop.application.order;

import org.dbs.shop.domain.shop.*;
import org.dbs.shop.domain.user.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderManagementImpl implements IOrderCreateUseCase, IGetOrderOfCustomerQuery {

    @Autowired
    IRepositoryUser repositoryUser;

    @Autowired
    IRepositoryOrder repositoryOrder;

    public Order placeOrder(@NotNull PlaceOrderCommand placeOrderCmd) {
        List<Item> items = new ArrayList<>();
        for (PlaceItemCommand placeItemCommand : placeOrderCmd.getPlaceItemCmds()) {
            Item item = new Item(placeItemCommand.getProductName(),
                    BigDecimal.valueOf(placeItemCommand.getPrice()),
                    placeItemCommand.getQuantity());
            items.add(item);
        }
        Order order = new Order(repositoryOrder.giveNextOrderNumber(), LocalDate.now(), items);

        //Customer customer = repositoryUser.findByName(placeOrderCmd.getCustomerName());
        //order.setCustomer(customer);

        repositoryOrder.save(order);

        return order;
    }

    @Override
    public List<Order> getOrderOfCustomer(String customerName) {
        return repositoryOrder.findOrderForCustomer(customerName);
    }

    @Override
    public Order getOrderOfCustomerByNumber(int orderNumber) {
        return repositoryOrder.findOrderByNumber(orderNumber);
    }

    @Override
    public List<Order> getAllOrders() {
        return repositoryOrder.findAllOrders();
    }
}
