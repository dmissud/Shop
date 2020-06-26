package org.dbs.shop.exposition.order;

import org.dbs.shop.common.AbstractMapper;
import org.dbs.shop.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper extends AbstractMapper<ItemDTO, Item> {

	@Override
	public ItemDTO mapToDto(final Item item) {
		final ItemDTO itemDTO = new ItemDTO();
		itemDTO.setDescription(item.getProductName());
		itemDTO.setPrice(item.getProductPrice());
		itemDTO.setQuantity(item.getQuantity());
		return itemDTO;
	}

	@Override
	public Item mapToDomain(final ItemDTO itemDTO) {
		return new Item(itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getQuantity());
	}

}