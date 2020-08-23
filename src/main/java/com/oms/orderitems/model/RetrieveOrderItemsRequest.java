package com.oms.orderitems.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveOrderItemsRequest {

    @Valid
    @NotEmpty(message="Please provide the product code(s)")
    private List<String> productCodes;
}
