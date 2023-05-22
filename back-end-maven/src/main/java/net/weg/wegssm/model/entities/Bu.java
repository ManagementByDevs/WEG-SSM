package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Bu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idBu;

    /**
     * Sigla da BU
     */
    @Column(length = 6)
    private String siglaBu;

    /**
     * Nome da BU
     */
    @Column(nullable = false, length = 100)
    private String nomeBu;

}
