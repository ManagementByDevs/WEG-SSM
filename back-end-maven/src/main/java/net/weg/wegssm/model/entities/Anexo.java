package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "anexo")
@AllArgsConstructor
@NoArgsConstructor()
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do arquivo
     */
    private String nome;

    /**
     * Tipo do arquivo
     */
    private String tipo;

    /**
     * Dados do arquivo em bytes
     */
    @Lob
    private byte[] dados;

}
