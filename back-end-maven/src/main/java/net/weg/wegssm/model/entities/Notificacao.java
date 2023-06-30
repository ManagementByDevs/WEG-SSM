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

    /**
     * Número sequencial da notificação
     */
    @Column(nullable = false)
    private String numeroSequencial;

    /**
     * Verificação se a notificação foi vista
     */
    @Column(nullable = false)
    private Boolean visualizado;

    /**
     * Data da notificação
     */
    @Column(nullable = false)
    private Date data;

    /**
     * Tipo da notificação
     */
    @Column(nullable = false)
    private TipoNotificacao tipoNotificacao;

    /**
     * Foreign key
     */

    /**
     * Usuário que recebeu a notificação
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Usuário que enviou a notificação
     */
    @ManyToOne
    @JoinColumn(name = "id_remetente", nullable = false)
    private Usuario remetente;

}
