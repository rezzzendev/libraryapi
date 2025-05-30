package com.rezzzendev.libraryapi.controller;

import com.rezzzendev.libraryapi.controller.dto.AutorDTO;
import com.rezzzendev.libraryapi.model.Autor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)//faz a mesma coisa que o PostMapping, porém passando parâmetro.
    public ResponseEntity salvar(@RequestBody AutorDTO autor) {
        return new ResponseEntity("Autor salvo com sucesso!" + autor, HttpStatus.CREATED);
    }
}
