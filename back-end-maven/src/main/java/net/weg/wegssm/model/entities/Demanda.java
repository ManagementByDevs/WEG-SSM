package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany
    @JoinColumn(name = "anexo_id")
    private List<Anexo> anexo;

}
