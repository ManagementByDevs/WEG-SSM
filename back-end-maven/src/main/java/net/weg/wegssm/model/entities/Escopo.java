package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "escopo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Escopo {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, unique = true)
        private Long id;

        @Column(nullable = false, length = 100)
        private String usuarioEmail;

        @Column(nullable = false, length = 50)
        private String titulo;

        @Column(nullable = false, length = 200)
        private String problema;

        @Column(nullable = false, length = 200)
        private String proposta;

        @Column(nullable = false, length = 30)
        private String frequencia;

        @Column
        private Boolean visibilidade;

}
