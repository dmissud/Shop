package org.dbs.shop.exposition.customer;

public class CustomerDTO {
    private String customerName;
    private String email;

    public CustomerDTO(String customerName, String email) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
