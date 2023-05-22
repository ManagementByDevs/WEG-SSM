package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "secao_ti")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SecaoTI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idSecao;

    /**
     * Sigla da seção
     */
    @Column(length = 6)
    private String siglaSecao;

    /**
     * Nome da seção
     */
    @Column(length = 100)
    private String nomeSecao;

}
