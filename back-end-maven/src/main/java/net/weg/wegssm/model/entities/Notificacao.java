package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notificacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String numeroSequencial;

    @Column(nullable = false)
    private Boolean visualizado;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private TipoNotificacao tipoNotificacao;

    // Foreign key

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

}
