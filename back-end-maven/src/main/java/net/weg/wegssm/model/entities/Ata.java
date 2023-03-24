package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ata")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Ata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Date dataReuniao;

    @Column(nullable = false, length = 14)
    private String numeroSequencial;

    @ManyToOne
    @JoinColumn(nullable = false, name = "comissao_id")
    private Forum comissao;

    @Column
    private Boolean visibilidade;

    @OneToMany
    @JoinColumn(name = "ata_id")
    private List<Proposta> propostas;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analistaResponsavel;
}
