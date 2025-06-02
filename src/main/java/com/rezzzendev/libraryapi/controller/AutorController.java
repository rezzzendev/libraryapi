package com.rezzzendev.libraryapi.controller;

import com.rezzzendev.libraryapi.controller.dto.AutorDTO;
import com.rezzzendev.libraryapi.model.Autor;
import com.rezzzendev.libraryapi.service.AutorService;
import jakarta.servlet.Servlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service){
        this.service = service;
    }

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)//faz a mesma coisa que o PostMapping, porém passando parâmetro.
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
        Autor autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
