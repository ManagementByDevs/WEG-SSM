package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ata")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Ata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Date inicioDataReuniao;

    @Column(nullable = false)
    private Date fimDataReuniao;

    @Column(nullable = false, length = 14)
    private String numeroSequencial;

    @Column
    private Boolean visibilidade;

}
