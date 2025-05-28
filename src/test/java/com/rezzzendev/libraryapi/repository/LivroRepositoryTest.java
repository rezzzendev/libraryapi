package com.rezzzendev.libraryapi.repository;

import com.rezzzendev.libraryapi.model.Autor;
import com.rezzzendev.libraryapi.model.GeneroLivro;
import com.rezzzendev.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("8f27e27c-df31-4601-8e94-390405c17fc1"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test//versão cascade
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setName("Ed");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test//usar essa manual, é a padrão
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("90899-84875");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("A vegetariana");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setName("Han Kang");
        autor.setNacionalidade("Coreano");
        autor.setDataNascimento(LocalDate.of(1975, 8, 31));

        autorRepository.save(autor);
        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        var livroParaAtualizar = repository.findById(UUID.fromString("9e90c355-3bc8-47e6-8439-9b0f032c1b0e")).orElse(null);

        UUID idAutor = UUID.fromString("0ea1abbf-7577-45bc-96a3-066cbc5b97a4");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("c3e3ee61-d1e4-496b-82e2-5c93b28e1d71");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade() {
        UUID id = UUID.fromString("9e90c355-3bc8-47e6-8439-9b0f032c1b0e");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("29ef175b-eb83-4255-a88f-6f03d1cef037");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
       /* System.out.println("Autor :");
        System.out.println(livro.getAutor().getName());*/
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Jobs");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("90900-90904");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(100.00);
        String tituloPesquisa = "Jobs";

        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros() {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros() {
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultado = repository.findByGenero(GeneroLivro.ROMANCE, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalParamTest() {
        var resultado = repository.findByGenero(GeneroLivro.ROMANCE, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        repository.deleteByGenero(GeneroLivro.ROMANCE);
    }

    @Test
    void updateDataPublicacaoTest() {
        repository.updateDataPublicacao(LocalDate.of(2000,6,6));
    }
}