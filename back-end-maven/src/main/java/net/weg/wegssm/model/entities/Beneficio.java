package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "beneficio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private TipoBeneficio tipoBeneficio;

    @Column
    private Double valor_mensal;

    @Column(length = 6)
    private String moeda;

    @Column(length = 200)
    private String memoriaCalculo;

}
