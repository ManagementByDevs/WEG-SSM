package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "custo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Tipo da despesa do custo
     */
    @Column
    private String tipoDespesa;

    /**
     * Perfil da despesa do custo
     */
    @Column
    private String perfilDespesa;

    /**
     * Período de execução do custo
     */
    @Column
    private Long periodoExecucao;

    /**
     * Quantidade de horas
     */
    @Column
    private Double horas;

    /**
     * Valor da hora
     */
    @Column
    private Double valorHora;

}
