package org.dbs.shop.infratructure.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class OrderNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int numberOfLastOrder;
    private LocalDate lastUpdateDate;
    private LocalTime lastUpdateTime;

    public int getNumberOfLastOrder() {
        return numberOfLastOrder;
    }

    public void setNumberOfLastOrder(int numberOfOrder) {
        this.numberOfLastOrder = numberOfOrder;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public LocalTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "OrderNumberEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfLastOrder=" + numberOfLastOrder +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }


}
