package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mensagem")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private Boolean visto;

    @Column(nullable = false)
    private String texto;

    @Column
    private String arquivo;

    // Foreign key

    @ManyToOne
    @JoinColumn(name = "id_chat")
    private Chat chat;

}
