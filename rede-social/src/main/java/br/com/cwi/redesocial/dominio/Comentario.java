package br.com.cwi.redesocial.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "COMENTARIO")
public class Comentario {

    private static final String SEQUENCE = "COMENTARIO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_COMENTARIO", nullable = false, precision = 10, unique = true)
    private Long id;

    @Column(name = "TEXTO", nullable = false,length = 128)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_POST")
    private Post post;


}
