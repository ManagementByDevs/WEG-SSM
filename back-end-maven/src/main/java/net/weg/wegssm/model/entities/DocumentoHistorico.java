package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "documento")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class DocumentoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do documento
     */
    @NonNull
    private String nome;

    /**
     * Tipo do documento
     */
    @NonNull
    private String tipo;

    /**
     * Dados do documento
     */
    @Lob
    @NonNull
    private byte[] dados;

}
