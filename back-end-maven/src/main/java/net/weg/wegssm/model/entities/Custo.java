package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "custo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 45)
    private String tipo;

    @Column(nullable = false, length = 45)
    private String perfil;

    @Column(nullable = false)
    private Long periodoExecucao;

    @Column(nullable = false)
    private Double horas;

    @Column(nullable = false)
    private Double valorHora;

    @Column
    private String css;

    // Foreign key

//    @ManyToOne
//    @JoinColumn(name = "proposta_id")
//    private Proposta proposta;

}
