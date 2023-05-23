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

    /**
     * Título do escopo da demanda
     */
    @Column(length = 100)
    private String titulo;

    /**
     * Problema do escopo da demanda, em bytes pois está em HTML
     */
    @Column
    @Lob
    private byte[] problema;

    /**
     * Proposta do escopo da demanda, em bytes pois está em HTML
     */
    @Column
    @Lob
    private byte[] proposta;

    /**
     * Frequência do escopo da demanda
     */
    @Column(length = 100)
    private String frequencia;

    /**
     * Data da última modificação do escopo da demanda
     */
    @Column
    private Date ultimaModificacao;

    /**
     * Foreign keys
     */

    /**
     * Lista de benefícios do escopo da demanda
     */
    @OneToMany
    @JoinColumn(name = "escopo_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    /**
     * Usuário que pertence ao escopo da demanda
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Lista de anexos do escopo da demanda
     */
    @OneToMany
    @JoinColumn(name = "id_escopo")
    private List<Anexo> anexo;

    /**
     * Função para adicionar anexos em um escopo da demanda
     *
     * @Param files Lista de arquivos a serem adicionados
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
