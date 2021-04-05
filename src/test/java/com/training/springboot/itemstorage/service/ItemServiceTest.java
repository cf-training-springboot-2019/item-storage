package com.training.springboot.itemstorage.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.error.EntityNotFoundException;
import com.training.springboot.itemstorage.repository.ItemRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	private static final long ID = 1l;
	@Mock
	private ItemRepository itemRepository;
	@InjectMocks
	private ItemService itemService;

	@Test
	public void save() {
		Item item = Item.builder().
				name("banana").priceTag(BigDecimal.ONE).stock(BigInteger.ONE).build();

		Item persist = item;
		persist.setItemUid(1l);
		when(itemRepository.save(item)).thenReturn(persist);
		item = itemService.save(item);
		assertThat(item.getItemUid(), is(1l));
	}

	@Test
	public void errorGet() {
		when(itemRepository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class,
				() -> itemService.get(ID));
	}

}