package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Nome do departamento
     */
    @Column(nullable = false, length = 50)
    private String nome;

    /**
     * Visibilidade do departamento, utilizada para não excluir direto do BD
     */
    @Column
    private Boolean visibilidade;

}
