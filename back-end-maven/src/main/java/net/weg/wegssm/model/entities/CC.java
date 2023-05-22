package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Código do centro de custo
     */
    @Column(name = "codigo")
    private Integer codigo;

    /**
     * Porcentagem do centro de custo
     */
    @Column(name = "porcentagem")
    private Double porcentagem;

}
