package org.dbs.shop.domain.delivery;

import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.Order;
import org.dbs.shop.domain.user.User;

import java.time.LocalDate;
import java.util.List;

public class Shipment {
    private int number;
    private LocalDate shipmentDate;
    private User responsible;
    private Customer customer;
    private List<Order> orders;
}
