package com.training.springboot.itemstorage.service;

import java.util.List;
import org.springframework.data.domain.Page;

public interface CrudService<T extends Object> {

	Page<T> list(int size, int page);

	List<T> list();

	T get(Long id);

	void delete(Long id);

	T update(T entity);

	T save(T entity);

}
