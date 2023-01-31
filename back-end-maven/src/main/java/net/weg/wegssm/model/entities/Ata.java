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
    private Date inicioDataReuniao;

    @Column(nullable = false)
    private Date fimDataReuniao;

    @Column(nullable = false, length = 14)
    private String numeroSequencial;

    @Column(nullable = false, length = 100)
    private String comissao;

    @Column
    private Boolean visibilidade;

    @OneToMany
    @JoinColumn(name = "ata_id")
    private List<Proposta> propostas;

}
