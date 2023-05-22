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

    /**
     * Tipo do benefício (REAL, POTENCIAL ou QUALITATIVO)
     */
    @Column
    private TipoBeneficio tipoBeneficio;

    /**
     * Valor mensal do benefício
     */
    @Column
    private Double valor_mensal;

    /**
     * Tipo da moeda
     */
    @Column(length = 6)
    private String moeda;

    /**
     * Memória de cálculo do benefício, utilizada em bytes pois utiliza HTML
     */
    @Column
    private byte[] memoriaCalculo;

}
