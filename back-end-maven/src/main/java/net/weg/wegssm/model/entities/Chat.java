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

    @Column(nullable = false)
    private Boolean conversa_encerrada;

    // Foreign keys

    @OneToOne
    @JoinColumn(name = "id_proposta", nullable = false)
    private Proposta idProposta;

    @OneToOne
    @JoinColumn(name = "id_demanda", nullable = true)
    private Demanda idDemanda;

    @ManyToMany
    @JoinTable(
            name = "usuario_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuariosChat;

}
