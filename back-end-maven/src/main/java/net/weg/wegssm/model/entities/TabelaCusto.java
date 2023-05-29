package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tabela_custo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TabelaCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Lista de custos da tabela de custos
     */
    @OneToMany
    @JoinColumn(name = "tabela_custo_id")
    private List<Custo> custos;

    /**
     * Lista de CCs da tabela de custos
     */
    @OneToMany
    @JoinColumn(name = "tabela_custo_id")
    private List<CC> ccs;

    @Column
    private String tipoDespesa;

}
