package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.entity.model.Item;

public interface IItemService extends ICrudService<Item> {

	void restock(Long id, Integer quantity);

	void dispatch(Long id, Integer quantity);

}
