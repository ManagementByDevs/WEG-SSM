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

    @Column
    private String tipoDespesa;

    @Column
    private String perfilDespesa;

    @Column
    private Long periodoExecucao;

    @Column
    private Double horas;

    @Column
    private Double valorHora;

}
