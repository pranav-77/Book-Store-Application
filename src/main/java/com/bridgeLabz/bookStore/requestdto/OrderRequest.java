package com.bridgeLabz.bookStore.requestdto;

import com.bridgeLabz.bookStore.model.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Address cannot be null")
    private Address address;
}

