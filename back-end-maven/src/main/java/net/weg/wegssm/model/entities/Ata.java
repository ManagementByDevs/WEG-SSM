package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ata")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Ata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Data em que a reunião foi realizada
     */
    @Column(nullable = false)
    private Date dataReuniao;

    /**
     * Número sequencial da ata
     */
    @Column(nullable = false, length = 14)
    private String numeroSequencial;

    /**
     * Número sequencial da ata da DG
     */
    @Column
    private String numeroSequencialDG;

    /**
     * Visibilidade da ata, utilizada para guardá-la antes de removê-la do banco de dados
     */
    @Column
    private Boolean visibilidade;


    /** Foreign keys */

    /**
     * Comissão presente na ata
     */
    @ManyToOne
    @JoinColumn(nullable = false, name = "comissao_id")
    private Forum comissao;

    /**
     * Lista de propostas presentes na ata
     */
    @OneToMany
    @JoinColumn(name = "ata_id")
    private List<Proposta> propostas;

    /**
     * Analista responsável pela ata
     */
    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analistaResponsavel;

    /**
     * Score da ata
     */
    @Column
    private Double score;

    /**
     * Ata publicada ou não publicada - DG
     */
    @Column
    private Boolean publicadaDg;

    /**
     * Ata publicada ou não publicada
     */
    @Column
    private Boolean publicada;

}
