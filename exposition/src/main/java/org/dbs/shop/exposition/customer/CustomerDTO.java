package org.dbs.shop.exposition.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer", description = "Customer datas")
@Validated
public class CustomerDTO {

	@NotNull
	private String customerName;

	public CustomerDTO(final String customerName) {
		this.customerName = customerName;
	}

	public CustomerDTO() {
	}

	@ApiModelProperty(example = "John Doe", required = true, value = "Customer name")
	@NotNull
	@Pattern(regexp = "^([a-zA-Z0-9]{1,30})$")
	@Size(max = 30)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}
}
