package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(length = 100)
    private String titulo;

    @Column
    @Lob
    private byte[] problema;

    @Column
    @Lob
    private byte[] proposta;

    @Column(length = 100)
    private String frequencia;

    @Column
    private Date ultimaModificacao;

    // Foreign key

    @OneToMany
    @JoinColumn(name = "escopo_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "id_escopo")
    private List<Anexo> anexo;

    /**
     * Função para adicionar anexos em um escopo
     */
    public void setAnexos(List<MultipartFile> files) {
        List<Anexo> listaAnexos = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                listaAnexos.add(new Anexo(null, file.getOriginalFilename(), file.getContentType(), file.getBytes()));
            }
            this.anexo = listaAnexos;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
