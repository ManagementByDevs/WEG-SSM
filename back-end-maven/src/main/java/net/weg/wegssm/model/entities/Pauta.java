package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pauta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroSequencial;

    @Column(nullable = false)
    private Date dataReuniao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "comissao_id")
    private Forum comissao;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analistaResponsavel;

    @OneToMany
    @JoinColumn(name = "pauta_id")
    private List<Proposta> propostas;

}
