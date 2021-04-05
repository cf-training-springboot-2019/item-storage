package com.training.springboot.itemstorage.utils.properties;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ItemStorageProperties {

	@Value("api.version")
	private String apiVersion;

}