package com.training.springboot.itemstorage.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.CreateItemRequest;
import com.training.springboot.itemstorage.entity.response.CreateItemResponse;
import com.training.springboot.itemstorage.enums.EnumItemState;
import com.training.springboot.itemstorage.service.ItemService;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

@WebMvcTest(controllers = ItemController.class)
class ItemControllerTest {

	private static Long ITEM_UID = 1l;

	@Value("classpath:samples/requests/createItemWhenValidReturn200Ok.json")
	private Resource createItemWhenValidReturn200OkRequest;
	@Value("classpath:samples/responses/createItemWhenValidReturn200Ok.json")
	private Resource createItemWhenValidReturn200OkResponse;
	@MockBean
	private ItemService itemService;
	@MockBean
	private ModelMapper modelMapper;
	@Autowired
	private MockMvc mockMvc;

	@Test
	@SneakyThrows
	void createItem() {

		CreateItemRequest request = CreateItemRequest.builder()
				.name("Item1")
				.description("description")
				.market("PT")
				.priceTag(10d)
				.stock(1).build();

		Item item = Item.builder()
				.name("Item1")
				.description("description")
				.market("PT")
				.priceTag(BigDecimal.TEN)
				.stock(BigInteger.ONE).build();

		Item persistedItem = Item.builder()
				.itemUid(ITEM_UID)
				.name("Item1")
				.description("description")
				.state(EnumItemState.AVAILABLE.name())
				.market("PT")
				.priceTag(BigDecimal.TEN)
				.stock(BigInteger.ONE).build();

		CreateItemResponse response = CreateItemResponse.builder().itemUid(ITEM_UID).build();

		when(modelMapper.map(request, Item.class)).thenReturn(item);
		when(itemService.save(item)).thenReturn(persistedItem);
		when(modelMapper.map(persistedItem, CreateItemResponse.class)).thenReturn(response);

		String requestContent = FileCopyUtils.copyToString(new FileReader(createItemWhenValidReturn200OkRequest.getFile()));

		mockMvc.perform(post("/items")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestContent)
		)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.itemUid").value(ITEM_UID));

	}
}