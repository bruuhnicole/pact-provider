package com.product.message;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class ProductCreatedMessage {

	@NotNull
	private String messageUuid;

	@NotNull
	private Product product;

}
