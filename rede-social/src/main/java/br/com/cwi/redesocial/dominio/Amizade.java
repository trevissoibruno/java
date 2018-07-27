package br.com.cwi.redesocial.dominio;

import br.com.cwi.redesocial.enumeration.StatusSolicitacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "AMIZADE")
public class Amizade {

    private static final String SEQUENCE = "AMIZADE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_AMIZADE", nullable = false, precision = 10, unique = true)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "ID_AMIGO")
    private Usuario amigo;


    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false, length = 20)
    private StatusSolicitacao situacao;
}
