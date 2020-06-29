package org.dbs.shop.application.order;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.dbs.shop.application.common.SelfValidating;
import org.dbs.shop.domain.Order;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IOrderCreateUseCase {
    Order placeOrder(PlaceOrderCommand placeOrderCmd);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class PlaceItemCommand extends SelfValidating<PlaceItemCommand> {
        @NotNull
        String productName;

        @NotNull
        int quantity;

        @NotNull
        double price;

        public PlaceItemCommand(@NotNull String productName, @NotNull int quantity, @NotNull double price) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
            this.validateSelf();
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    class PlaceOrderCommand extends SelfValidating<PlaceOrderCommand> {

        @NotNull
        String customerName;

        @NotNull
        List<PlaceItemCommand> placeItemCmds;

        public PlaceOrderCommand(
                String customerName,
                List<PlaceItemCommand> placeItemCmds) {
            this.customerName = customerName;
            this.placeItemCmds = placeItemCmds;
            this.validateSelf();
        }
    }
}
