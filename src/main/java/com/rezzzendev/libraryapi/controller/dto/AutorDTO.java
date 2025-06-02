package com.rezzzendev.libraryapi.controller.dto;

import com.rezzzendev.libraryapi.model.Autor;

import java.time.LocalDate;

public record AutorDTO(
        String name,
        LocalDate dataNascimento,
        String nacionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setName(this.name);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
