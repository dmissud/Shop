package org.dbs.shop.infrastructure.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.dbs.shop.infrastructure.customer.CustomerEntity;
import org.dbs.shop.infrastructure.item.ItemEntity;

@Entity
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private CustomerEntity customer;

	private int number;

	private LocalDate orderDate;

	private LocalDate shipmentDate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEntity> items = new ArrayList<>();

	/**
	 * Default constructor needed by Hibernate
	 */
	public OrderEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(final CustomerEntity customer) {
		this.customer = customer;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(final LocalDate shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(final List<ItemEntity> items) {
		this.items = items;
	}

}
