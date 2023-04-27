package net.weg.wegssm.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    @JsonIgnore
    private String senha;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column
    private Boolean visibilidade;

    @Enumerated
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(nullable = false, length = 2000)
    private String preferencias;

    // Foreign key

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}
