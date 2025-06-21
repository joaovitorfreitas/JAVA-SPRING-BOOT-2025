package io.springjpa.course.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
//antigamente era javax, alterado para JAKARTA

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
@ToString
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDateTime dataNascimento;

    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    //mappedBy deixa marcado que livros não é uma coluna, apenas refer
    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    @Deprecated
    public Autor() {
        // Construtor vázio utilizado pelo JPA.
    }
}
