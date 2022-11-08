package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "historico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Long numeroVersao;

    @Column(nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentoHistorico documento;

    @Column(nullable = false , length = 45)
    private String acaoRealizada;

    @ManyToOne
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    @Column
    private Boolean visibilidade;

}
