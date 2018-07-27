package br.com.cwi.redesocial.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "POST")
public class Post {

    private static final String SEQUENCE = "POST_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_POST", nullable = false, precision = 10, unique = true)
    private Long id;

    @Column(name = "TEXTO", nullable = false,length = 512)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "IMAGEM", nullable = false,length = 512)
    private String imagem;

    @Column(name = "TITULO", nullable = false,length = 100)
    private String titulo;

    @Column(name = "DATA_POSTAGEM")
    private LocalDateTime dataHoraPostagem;


    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Avaliacao> avaliacoes;




}
