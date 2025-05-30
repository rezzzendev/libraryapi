package com.rezzzendev.libraryapi.controller.dto;

import java.time.LocalDate;

public record AutorDTO(
        String name,
        LocalDate dataNascimento,
        String nacionalidade) {
}
