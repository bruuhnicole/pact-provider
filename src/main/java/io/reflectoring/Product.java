package io.reflectoring;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Product {

	@NotNull
	private long id;

	@NotNull
	private String name;

}
