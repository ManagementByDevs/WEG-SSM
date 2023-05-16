package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "anexo")
@AllArgsConstructor @NoArgsConstructor()
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    @Lob
    private byte[] dados;
}
