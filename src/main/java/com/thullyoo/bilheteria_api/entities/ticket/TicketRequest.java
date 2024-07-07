package com.thullyoo.bilheteria_api.entities.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TicketRequest(@NotNull(message = "chair não pode ser null") @NotBlank(message = "chair não pode ficar em branco")String chair,
                            @NotNull(message = "value não pode ser null") @Positive(message = "value não pode ser negativo")Double value) {
}
