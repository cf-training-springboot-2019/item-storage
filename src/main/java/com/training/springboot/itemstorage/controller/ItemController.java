package com.training.springboot.itemstorage.controller;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.CreateItemRequest;
import com.training.springboot.itemstorage.entity.request.DispatchItemRequest;
import com.training.springboot.itemstorage.entity.request.RestockItemRequest;
import com.training.springboot.itemstorage.entity.request.UpdateItemRequest;
import com.training.springboot.itemstorage.entity.response.CreateItemResponse;
import com.training.springboot.itemstorage.entity.response.GetItemResponse;
import com.training.springboot.itemstorage.entity.response.UpdateItemResponse;
import com.training.springboot.itemstorage.service.ItemService;
import com.training.springboot.itemstorage.utils.annotation.ServiceOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController implements StandardItemController {

	private final ItemService itemService;
	/**
	 * @JavaDoc ModelMapper is a mapping tool easily configurable to accommodate most application defined entities check
	 * some configuration example at: http://modelmapper.org/user-manual/
	 */
	private final ModelMapper mapper;

	@Override
	@PostMapping
	@ServiceOperation("createItem")
	public ResponseEntity<CreateItemResponse> createItem(@RequestBody @Valid CreateItemRequest request) {
		return new ResponseEntity<>(mapper.map(itemService.save(mapper.map(request, Item.class)), CreateItemResponse.class),
				HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{id}")
	@ServiceOperation("getItem")
	public ResponseEntity<GetItemResponse> getItem(@PathVariable("id") Long id) {
		return ResponseEntity.ok(mapper.map(itemService.get(id), GetItemResponse.class));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UpdateItemResponse> updateItem(@PathVariable("id") Long id,
			@RequestBody UpdateItemRequest request) {
		request.setItemUid(id);
		return ResponseEntity.ok(mapper.map(itemService.update(mapper.map(request, Item.class)), UpdateItemResponse.class));
	}

	@Override
	@DeleteMapping("/{id}")
	@ServiceOperation("deleteItem")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping
	@ServiceOperation("listItems")
	public ResponseEntity<List<GetItemResponse>> listItems() {
		List<Item> itemList = itemService.list();
		List<GetItemResponse> response =itemList.stream()
				.map(i -> mapper.map(i, GetItemResponse.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/{id}/dispatch")
	@ServiceOperation("dispatchItem")
	public ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequest request) {
		itemService.dispatch(id, request.getQuantity());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@PostMapping("/{id}/restock")
	@ServiceOperation("restockItem")
	public ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequest request) {
		itemService.restock(id, request.getQuantity());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
