package com.thullyoo.bilheteria_api.entities.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovieRequest(@NotNull(message = "image não pode ser null") @NotBlank(message = "image não pode ficar em branco") String image,
                           @NotNull(message = "name não pode ser null") @NotBlank(message = "name não pode ficar em branco")String name,
                           @NotNull(message = "description não pode ser null") @NotBlank(message = "description não pode ficar em branco")String description,
                           @NotEmpty(message = "actors não pode ser vazio") @NotNull(message = "actors não pode ser null")List<String> actors,
                           @NotNull(message = "genre não pode ser null") @NotBlank(message = "genre não pode ficar em branco")String genre,
                           @NotNull(message = "release_date não pode ser null") @NotBlank(message = "release_date não pode ficar em branco")String release_date) {
}
