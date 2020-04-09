package com.product.message;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
class Product {

	@NotNull
	private long id;

	@NotNull
	private String name;

}
