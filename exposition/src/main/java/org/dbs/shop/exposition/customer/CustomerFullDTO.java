package org.dbs.shop.exposition.customer;

public class CustomerFullDTO {
    private String customerName;
    private String password;

    public CustomerFullDTO() {}

    public CustomerFullDTO(String customerName, String password) {
        this.customerName = customerName;
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerFullDTO{" +
                "customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
