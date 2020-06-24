package org.dbs.shop.exposition.customer;

public class CustomerDTO {
    private String customerName;

    public CustomerDTO(String customerName) {
        this.customerName = customerName;
    }

    public CustomerDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
