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
@Table(name = "AVALIACAO")
public class Avaliacao {

    private static final String SEQUENCE = "AVALIACAO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_AVALIACAO", nullable = false, precision = 10, unique = true)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_POST")
    private Post post;
}
