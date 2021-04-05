package com.training.springboot.itemstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Add annotation to enable service discovery (as a client)
@SpringBootApplication
public class ItemStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemStorageApplication.class, args);
	}

}
