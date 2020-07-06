package org.dbs.shop.infratructure.order;

import org.dbs.shop.infratructure.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private LocalDate orderDate;
    private LocalDate shipmentDate;

    @ElementCollection
    private List<ItemEntity> items;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity customer;

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDate shipementDate) {
        this.shipmentDate = shipementDate;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
