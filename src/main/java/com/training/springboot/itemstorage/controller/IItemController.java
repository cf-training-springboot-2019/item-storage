package com.training.springboot.itemstorage.controller;

import com.training.springboot.itemstorage.entity.request.CreateItemRequestDto;
import com.training.springboot.itemstorage.entity.request.DispatchItemRequestDto;
import com.training.springboot.itemstorage.entity.request.RestockItemRequestDto;
import com.training.springboot.itemstorage.entity.request.UpdateItemRequestDto;
import com.training.springboot.itemstorage.entity.response.CreateItemResponseDto;
import com.training.springboot.itemstorage.entity.response.GetItemResponseDto;
import com.training.springboot.itemstorage.entity.response.UpdateItemResponseDto;
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

public interface IItemController {

	@PostMapping
	ResponseEntity<CreateItemResponseDto> createItem(@RequestBody @Valid CreateItemRequestDto request);

	@GetMapping("/{id}")
	ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id);

	@PatchMapping("/{id}")
	ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id,
			@RequestBody UpdateItemRequestDto request);

	@DeleteMapping("/{id}")
	ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id);

	@GetMapping
	ResponseEntity<List<GetItemResponseDto>> listItems();

	@PostMapping("/{id}/dispatch")
	ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequestDto request);

	@PostMapping("/{id}/restock")
	ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequestDto request);
}
