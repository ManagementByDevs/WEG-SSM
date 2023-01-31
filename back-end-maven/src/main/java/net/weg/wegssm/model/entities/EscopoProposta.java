package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "anexo")
@AllArgsConstructor
@NoArgsConstructor() @RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EscopoProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NonNull
    private byte[] dados;
}
