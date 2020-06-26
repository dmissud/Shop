package org.dbs.shop.infrastructure.item;

import org.dbs.shop.domain.Item;
import org.dbs.shop.infrastructure.AbstractMapper;
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