package com.rezzzendev.libraryapi.repository;

import com.rezzzendev.libraryapi.model.Autor;
import com.rezzzendev.libraryapi.model.GeneroLivro;
import com.rezzzendev.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    //Query method
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //JPQL


    @Query("select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    @Query(" select a from Livro as l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();


    @Query(" select distinct l.titulo from Livro l ")
    List<String> listarTitulosNaoRepetidosDosLivros();


    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    // parametros nomeados
    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao ")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String nomePropriedade);

    // parametros por posicao
    @Query("select l from Livro l where l.genero = ?1 order by ?2 ")
    List<Livro> findByGeneroPositionalParam(GeneroLivro generoLivro, String nomePropriedade);
}
