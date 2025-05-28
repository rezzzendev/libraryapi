package com.rezzzendev.libraryapi.service;

import com.rezzzendev.libraryapi.model.Autor;
import com.rezzzendev.libraryapi.model.GeneroLivro;
import com.rezzzendev.libraryapi.model.Livro;
import com.rezzzendev.libraryapi.repository.AutorRepository;
import com.rezzzendev.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("c1a90823-3a0f-480f-96e0-f608b94de61b")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2025, 05, 27));
    }

    @Transactional
    public void executar() {
        //salvar autor
        Autor autor = new Autor();
        autor.setName("Teste roll");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1944, 8, 31));

        autorRepository.save(autor);

        //salvar livro
        Livro livro = new Livro();
        livro.setIsbn("90877-84877");
        livro.setPreco(BigDecimal.valueOf(10));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Livro do Dede");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getName().equals("Teste roll")) {
            throw new RuntimeException("Rollback!");
        }
    }
}
