package org.dbs.shop.domain;

import java.util.Date;
import java.util.List;

public class Order {

    private final int number;
    private Date dateOrder;
    private Date dateShip;

    private float total;

    private List<Item> items;

    public Order(int number, Date dateOrder) {
        this.number = number;
        this.dateOrder = dateOrder;
    }



}
