package com.product.message;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
class ProductCreatedMessage {

	@NotNull
	private String messageUuid;

	@NotNull
	private Product product;

}
