package org.dbs.shop.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Validation des Items")
class ItemTest {

    @Test
    @DisplayName("Value for quantity with no discount")
    void should_have_no_discount_if_quantity_less_than_ten() {
        // Given
        Item item = new Item(RandomStringUtils.randomAlphanumeric(10), BigDecimal.valueOf(140), 5);

        // When
        BigDecimal cost = item.getCost();

        // Then
        assertThat(cost).isEqualByComparingTo(BigDecimal.valueOf(140*5));
    }

    @Test
    @DisplayName("Value for quantity with discount of 10%")
    void should_have_10pourcent_discount_if_quantity_more_than_ten() {
        // Given
        Item item = new Item(RandomStringUtils.randomAlphanumeric(10), BigDecimal.valueOf(140), 15);

        // When
        BigDecimal cost = item.getCost();

        // Then
        assertThat(cost).isCloseTo(BigDecimal.valueOf(140*15* Item.DISCOUNT_POURCENTAGE), Percentage.withPercentage(99.9));
    }

}