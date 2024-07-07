package com.thullyoo.bilheteria_api.entities.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SessionRequest(@NotNull(message = "room não pode ser null") @NotBlank(message = "room não pode ficar em branco")String room,
                             @NotNull(message = "Integer não pode ser null") @Positive(message = "capacity não pode ser negativo")Integer capacity,
                             @NotNull(message = "day não pode ser null") @NotBlank(message = "day não pode ficar em branco")String day,
                             @NotNull(message = "time não pode ser null") @NotBlank(message = "time não pode ficar em branco")String time) {
}
