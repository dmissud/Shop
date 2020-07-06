package org.dbs.shop.infratructure.order;

import org.dbs.shop.domain.shop.Item;
import org.dbs.shop.infratructure.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemEntityMapper extends AbstractMapper<Item, ItemEntity> {
    @Override
    public Item mapToDomain(final ItemEntity entity) {
        return new Item(entity.getProductName(), entity.getProductPrice(), entity.getQuantity());
    }

    @Override
    public ItemEntity mapToEntity(final Item item) {
        final ItemEntity itemEntity = new ItemEntity();
        itemEntity.setProductName(item.getProductName());
        itemEntity.setProductPrice(item.getProductPrice());
        itemEntity.setQuantity(item.getQuantity());
        return itemEntity;
    }
}
