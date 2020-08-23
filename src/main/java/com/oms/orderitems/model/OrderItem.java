package com.oms.orderitems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @NotBlank(message = "product code should not be blank")
    private String productCode;

    private String productName;

    @NotNull(message="Please provide Quantity")
    @Min(value=1,message="Quantity should be minimum 1")
    @Max(value=5,message="Quantity should be maximum 5")
    private Integer quantity;

}
