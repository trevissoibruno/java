package br.com.cwi.redesocial.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "email")
@Entity
@Table(name = "USUARIO")
public class Usuario {

    private static final String SEQUENCE = "USUARIO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_USUARIO", nullable = false, precision = 10, unique = true)
    private Long id;

    @Column(name = "NOME", nullable = false,length = 255)
    private String nome;

    @Column(name = "EMAIL", nullable = false,length = 255,unique = true)
    private String email;

    @Column(name = "APELIDO",length = 50)
    private String apelido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "SENHA", nullable = false,length = 256)
    private String senha;

    @Column(name = "IMAGEM",length = 512)
    private String imagem;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;


    @JsonIgnore
    @OneToMany(mappedBy = "amigo")
    private List<Amizade> amizades;











}
