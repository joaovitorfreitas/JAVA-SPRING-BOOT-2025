package io.springjpa.course.libraryapi.repository;

import io.springjpa.course.libraryapi.model.Autor;
import io.springjpa.course.libraryapi.model.GeneroLivro;
import io.springjpa.course.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;
    @Test
    void salvarLivro() {
        Livro livro = new Livro();
        livro.setIsbn("1234");
        livro.setPreco(new BigDecimal("20.00"));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Livro 1");
        livro.setDataPublicacao(LocalDateTime.now());

        Autor autor = autorRepository
                .findById(UUID.fromString("dd4c2ba8-217c-497c-9f72-265935e691ec"))
                .orElse(null);

        livro.setAutor(new Autor());
        livroRepository.save(livro);
    }

    @Test
    void salvarLivroCascade() {
        Livro livro = new Livro();
        livro.setIsbn("12354");
        livro.setPreco(new BigDecimal("20.00"));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Livro 2");
        livro.setDataPublicacao(LocalDateTime.now());

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setDataNascimento(LocalDateTime.now());
        autor.setNacionalidade("Brasileiro");

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        Livro livro = livroRepository.findById(UUID.fromString("8f66304f-e5f6-4822-94fb-2b3ff5afbd84")).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }
}