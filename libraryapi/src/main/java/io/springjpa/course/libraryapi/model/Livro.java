package io.springjpa.course.libraryapi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.util.Lazy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "livro", schema = "public")
@Data
//Data ==
//Getter
//Setter
//EqualsHashAndCode
//RequiredArgsConstructor

//não fazem parte do Data !=
//NoArgsConstructor
//AllArgsConstructor
public class Livro {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDateTime dataPublicacao;

    //Garante que guarda uma string lá no banco de dados
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false, length = 30)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    //Many entidade atual
    //one entidade mapeada
    @ManyToOne(fetch = FetchType.LAZY)
    //(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_autor")
    private Autor autor;

}
