package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "forum")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idForum;

    /**
     * Sigla do fórum
     */
    @Column(length = 6)
    private String siglaForum;

    /**
     * Nome do fórum
     */
    @Column(nullable = false, length = 100)
    private String nomeForum;

}
