package com.rezzzendev.libraryapi.repository;

import com.rezzzendev.libraryapi.model.Autor;
import com.rezzzendev.libraryapi.model.GeneroLivro;
import com.rezzzendev.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setName("Marcos");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2005, 1, 31));

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

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTeste() {
        var id = UUID.fromString("85061555-6438-4fbd-a91d-841c2b074890");
        repository.deleteById(id);
    }

    @Test
    public void deleteTeste() {
        var id = UUID.fromString("958c1510-abb9-460f-a75c-2dc02234e80b");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    public void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setName("Antonio");
        autor.setNacionalidade("Angolano");
        autor.setDataNascimento(LocalDate.of(1970, 1, 31));

        Livro livro = new Livro();
        livro.setIsbn("90907-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Jobs");
        livro.setDataPublicacao(LocalDate.of(2022, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90900-90904");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.ROMANCE);
        livro2.setTitulo("A culpa");
        livro2.setDataPublicacao(LocalDate.of(2011, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

}
