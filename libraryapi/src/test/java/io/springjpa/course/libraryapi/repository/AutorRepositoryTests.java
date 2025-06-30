package io.springjpa.course.libraryapi.repository;

import io.springjpa.course.libraryapi.model.Autor;
import io.springjpa.course.libraryapi.model.GeneroLivro;
import io.springjpa.course.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTests {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Test
    public void salvarRegistroTest() {
        Autor autor = new Autor();
        autor.setNome("Fulano");
        autor.setDataNascimento(LocalDateTime.now());
        autor.setNacionalidade("Brasileiro");

        var autoSaved =	 autorRepository.save(autor);
        System.out.println("Autor salvo: " + autoSaved);
    }

    @Test
    public void atualizarRegistroTest() {
        UUID id = UUID.fromString("888b6a64-fc55-40bf-8834-dca3ba8da37e");

        Optional<Autor> autor = autorRepository.findById(id);

        if(autor.isPresent()) {
            Autor findAutor = autor.get();
            System.out.println("dados autor");
            System.out.println(findAutor);
            findAutor.setDataNascimento(LocalDateTime.now());
            autorRepository.save(findAutor);

        }
    }

    @Test
    public void listarRegistrosTest() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    public void countRegistroTest() {
        System.out.println("contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletarRegistroByIdTest() {
        UUID id = UUID.fromString("888b6a64-fc55-40bf-8834-dca3ba8da37e");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteRegistroTest() {
        UUID id = UUID.fromString("076ee37f-a9db-4067-9286-ebfb1381dcc9");
        Autor autor = autorRepository.findById(id).get() ;
        autorRepository.delete(autor);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDateTime.now());

        Livro livro = new Livro();
        livro.setIsbn("1234");
        livro.setPreco(new BigDecimal("20.00"));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("ENABLE 4242");
        livro.setDataPublicacao(LocalDateTime.now());
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("12345");
        livro2.setPreco(new BigDecimal("20.00"));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("UNABLE 4242");
        livro2.setDataPublicacao(LocalDateTime.now());
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    //Não utilizar @Transactional para consultar livros do autor utilizando lazy no JPA.
    void listarLivrosDoAutorTest(){
        UUID id = UUID.fromString("0270994d-ce6c-424d-8dbe-d628b38b8d67");
        Autor autor = autorRepository.findById(id).get() ;

        //buscar livros do autor
        List<Livro> livros = livroRepository.findByAutor(autor);

        System.out.println("Livros do autor: ");
        livros.forEach(System.out::println);
    }
}
