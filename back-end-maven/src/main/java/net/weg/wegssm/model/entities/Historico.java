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
    private Date data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentoHistorico documento;

    @Column(nullable = false , length = 45)
    private String acaoRealizada;

    @Column
    private Boolean visibilidade;

    // Foreign key

    @ManyToOne
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    @ManyToOne
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

}
