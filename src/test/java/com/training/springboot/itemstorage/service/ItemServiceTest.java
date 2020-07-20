package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	private static Long ITEM_UID = 1l;
	@InjectMocks
	private ItemService itemService;
	@Mock
	private ItemRepository itemRepository;

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	public void list() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test
	public void testList() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test
	public void get() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test
	public void getEntityNotFoundThrown() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
		// - EntityNotFoundException is thrown
	}

}