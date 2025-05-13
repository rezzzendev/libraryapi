package com.rezzzendev.libraryapi.repository;

import com.rezzzendev.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setName("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1960, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
       var id = UUID.fromString("1a27c009-ca64-4ad7-ad27-d894ecf2a6f3");

       Optional<Autor> possivelAutor = repository.findById(id);

       if(possivelAutor.isPresent()) {

           Autor autorEncontrado = possivelAutor.get();
           System.out.println("Dados do autor: ");
           System.out.println(possivelAutor.get());

           autorEncontrado.setDataNascimento(LocalDate.of(1980, 1, 30));

           repository.save(autorEncontrado);
       }
    }
}
