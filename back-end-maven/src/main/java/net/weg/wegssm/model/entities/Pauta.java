package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Long numeroSequencial;

    @Column(nullable = false)
    private Date inicioDataReuniao;

    @Column(nullable = false)
    private Date fimDataReuniao;

    @Column(nullable = false)
    private String comissao;

    @Column
    private Boolean visibilidade;
}
