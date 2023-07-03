package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Utilizada para verificar se a conversa foi encerrada
     */
    @Column(nullable = false)
    private Boolean conversaEncerrada;

    /**
     * Mensagens que ainda não foram lidas
     */
    @Column
    private Long msgNaoLidas = Long.parseLong("0");

    /**
     * Foreign keys
     */

    /**
     * Proposta que o chat está relacionado
     */
    @OneToOne
    @JoinColumn(name = "id_proposta")
    private Proposta idProposta;

    @OneToOne
    @JoinColumn(name = "id_demanda")
    private Demanda idDemanda;

    /**
     * Lista de usuários que pertencem ao chat
     */
    @ManyToMany
    @JoinTable(
            name = "usuario_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuariosChat;

}
