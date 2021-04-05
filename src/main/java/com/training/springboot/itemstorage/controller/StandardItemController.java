package com.training.springboot.itemstorage.controller;

import com.training.springboot.itemstorage.entity.request.CreateItemRequest;
import com.training.springboot.itemstorage.entity.request.DispatchItemRequest;
import com.training.springboot.itemstorage.entity.request.RestockItemRequest;
import com.training.springboot.itemstorage.entity.request.UpdateItemRequest;
import com.training.springboot.itemstorage.entity.response.CreateItemResponse;
import com.training.springboot.itemstorage.entity.response.GetItemResponse;
import com.training.springboot.itemstorage.entity.response.UpdateItemResponse;
import com.training.springboot.itemstorage.utils.annotation.ServiceOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StandardItemController {

	@PostMapping
	@ServiceOperation("createItem")
	ResponseEntity<CreateItemResponse> createItem(@RequestBody @Valid CreateItemRequest request);

	@GetMapping("/{id}")
	@ServiceOperation("getItem")
	ResponseEntity<GetItemResponse> getItem(@PathVariable("id") Long id);

	@PatchMapping("/{id}")
	@ServiceOperation("updateItem")
	ResponseEntity<UpdateItemResponse> updateItem(@PathVariable("id") Long id, @RequestBody UpdateItemRequest item);

	@DeleteMapping("/{id}")
	@ServiceOperation("deleteItem")
	ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id);

	@GetMapping
	@ServiceOperation("listItems")
	ResponseEntity<List<GetItemResponse>> listItems();

	@PostMapping("/{id}/dispatch")
	@ServiceOperation("dispatchItem")
	ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequest request);

	@PostMapping("/{id}/restock")
	@ServiceOperation("restockItem")
	ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequest request);
}
