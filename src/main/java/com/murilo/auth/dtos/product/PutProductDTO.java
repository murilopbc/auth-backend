package com.murilo.auth.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PutProductDTO(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotNull
        Integer price
) {
}
