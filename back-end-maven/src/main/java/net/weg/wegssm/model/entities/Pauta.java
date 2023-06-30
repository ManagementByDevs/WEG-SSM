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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Número sequencial da pauta
     */
    @Column(nullable = false, unique = true)
    private String numeroSequencial;

    /**
     * Data de reunião da pauta
     */
    @Column(nullable = false)
    private Date dataReuniao;

    /**
     * Foreign keys
     */

    /**
     * Comissão a qual a pauta pertence
     */
    @ManyToOne
    @JoinColumn(nullable = false, name = "comissao_id")
    private Forum comissao;

    /**
     * Analista responsável pela pauta
     */
    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analistaResponsavel;

    /**
     * Lista de propostas que a pauta possui
     */
    @OneToMany
    @JoinColumn(name = "pauta_id")
    private List<Proposta> propostas;

    /**
     * Score de uma pauta
     */
    @Column
    private Double score;

}
