package org.dbs.shop.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {


    @Test
    @DisplayName("The Total of the order is ok for a set of item")
    void should_have_a_correct_total_for_all_the_items_that_i_add() {
        // Given
        List<Item> items = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < 10; i++) {
            Item item = new Item(RandomStringUtils.randomAlphanumeric(10),
                    new BigDecimal(RandomUtils.nextInt(4, 150)),
                    RandomUtils.nextInt(1, 15));
            total = total.add(item.getCost());
            items.add(item);
        }

        // When
        Order order = new Order(RandomUtils.nextInt(1000, 10000000),
                LocalDate.now(), items);

        // Then
        assertThat(order.getTotalAmount()).isEqualByComparingTo(total);
    }

    @Test
    @DisplayName("The Total of the order is ok for a item")
    void should_have_a_correct_total_for_a_item_that_i_add() {
        // Given
        List<Item> items = new LinkedList<>();
        items.add(new Item("Produit1", BigDecimal.valueOf(1),1));
        Order order = new Order(RandomUtils.nextInt(1000, 10000000),
                LocalDate.now(), items);
        BigDecimal total = order.getTotalAmount();
        Item item = new Item(RandomStringUtils.randomAlphanumeric(10),
                new BigDecimal(RandomUtils.nextInt(4, 15)),
                RandomUtils.nextInt(10, 150));
        total = total.add(item.getCost());

        // When
        order.addItem(item);

        // Then
        assertThat(order.getTotalAmount()).isEqualByComparingTo(total);
    }
}