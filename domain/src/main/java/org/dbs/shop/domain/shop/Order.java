package org.dbs.shop.domain.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.math.BigDecimal.*;

public class Order {

    private Customer customer;

    private final int number;
    private LocalDate orderDate;
    private LocalDate shipmentDate;

    private BigDecimal total;

    private List<Item> items;

    public Order(int number, LocalDate orderDate, List<Item> items) {
        this.number = number;
        this.orderDate = orderDate;
        this.total = new BigDecimal(0);
        this.items = new LinkedList<>();
        addItems(items);
    }


    public int getNumber() {
        return this.number;
    }

    public void addItems(List<Item> items) {
        for(Item item:items) {
            addItem(item);
        }
    }

    public BigDecimal getTotalAmount() {
        if (this.total.compareTo(ZERO) == 0) {
            for(Item item:this.items) {
                this.total = this.total.add(item.getCost());
            }
        }
        return this.total;
    }

    public void addItem(Item item) {
        this.items.add(item);
        this.total = this.total.add(item.getCost());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getItemCount() {
        return this.items.size();
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

}
