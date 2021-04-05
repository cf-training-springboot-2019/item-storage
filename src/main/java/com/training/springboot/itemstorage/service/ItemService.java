package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.enums.EnumEntity;
import com.training.springboot.itemstorage.enums.EnumItemState;
import com.training.springboot.itemstorage.error.EntityNotFoundException;
import com.training.springboot.itemstorage.repository.ItemRepository;

import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ItemService implements StandardItemService {

	private final ItemRepository itemRepository;

	@Override
	public Page<Item> list(int size, int page) {
		return null;
	}

	@Override
	public List<Item> list() {
		return itemRepository.findAll();
	}

	@Override
	public Item get(Long id) {
		return itemRepository.findById(id).orElseThrow(() ->
				new EntityNotFoundException(EnumEntity.ITEM.name(), id));
	}

	@Override
	public void delete(Long id) {
		itemRepository.delete(get(id));
	}

	@Override
	public Item update(Item item) {
		Item persistedItem = get(item.getItemUid());
		if (!StringUtils.isEmpty(item.getName())) {
			persistedItem.setName(item.getName());
		}
		if (!StringUtils.isEmpty(item.getDescription())) {
			persistedItem.setDescription(item.getDescription());
		}
		if (!StringUtils.isEmpty(item.getMarket())) {
			persistedItem.setMarket(item.getMarket());
		}
		if (item.getStock() != null && item.getStock().intValue() >= 0) {
			persistedItem.setStock(item.getStock());
		}
		if (item.getPriceTag() != null && item.getPriceTag().longValue() >= 0.0) {
			persistedItem.setPriceTag(item.getPriceTag());
		}
		return persistedItem;
	}

	@Override
	public Item save(Item item) {
		item.setState(EnumItemState.AVAILABLE.name());
		return itemRepository.save(item);
	}


	@Override
	public void restock(Long id, Integer quantity) {
		Item item = get(id);
		item.setStock(item.getStock().add(BigInteger.valueOf(quantity)));
		save(item);
	}

	@Override
	public void dispatch(Long id, Integer quantity) {
		Item item = get(id);
		item.setStock(item.getStock().subtract(BigInteger.valueOf(quantity)));
		save(item);
	}
}
