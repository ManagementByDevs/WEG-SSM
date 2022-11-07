package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "demanda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 200)
    private String problema;

    @Column(nullable = false, length = 200)
    private String proposta;

    @Column(nullable = false, length = 30)
    private String frequencia;

    @Column(length = 20)
    private String tamanho;

    @Column(length = 45)
    private String buSolicitante;

    @Column(length = 45)
    private String busBeneficiadas;

    @Column(length = 45)
    private String secaoTI;

    @Column(length = 200)
    private String motivoRecusa;

    @Column(nullable = false)
    private Status status;

    @Column
    private Boolean visibilidade;

    // foreign keys

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

}
