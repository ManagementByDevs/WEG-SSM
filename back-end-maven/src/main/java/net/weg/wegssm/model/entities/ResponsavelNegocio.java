package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "responsavel_negocio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponsavelNegocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Nome do responsável negócio
     */
    @Column(length = 100)
    private String nome;

    /**
     * Área do responsável negócio
     */
    @Column(length = 100)
    private String area;

}
