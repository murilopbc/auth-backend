package com.murilo.auth.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostProductDTO(
        @NotBlank
        String name,

        @NotNull
        Integer price
) {

}