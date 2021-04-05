package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.entity.model.Item;

public interface StandardItemService extends CrudService<Item> {

	void restock(Long id, Integer quantity);

	void dispatch(Long id, Integer quantity);

}
