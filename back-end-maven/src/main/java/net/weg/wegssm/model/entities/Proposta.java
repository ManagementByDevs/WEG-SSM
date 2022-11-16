package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proposta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 200)
    private String escopo;

    @Column(nullable = false)
    private Date inicioExecucao;

    @Column(nullable = false)
    private Date fimExecucao;

    @Column(nullable = false)
    private Double paybackValor;

    @Column(nullable = false, length = 45)
    private String paybackTipo;

    @Column(nullable = false)
    private Long codigoPPM;

    @Column(nullable = false, length = 100)
    private String linkJira;

    @Column(nullable = false)
    private Boolean publicada;

    @Column(nullable = false)
    private Boolean naoPublicada;

    @Column(nullable = false)
    private Status status;

    @Column
    private ParecerGerencia parecerComissao;

    @Column(length = 200)
    private String parecerInformacao;

    @Column
    private ParecerGerencia parecerDG;

    @Column
    private Boolean visibilidade;

    // Foreign keys

    @OneToMany
    @JoinColumn(name = "custo_id")
    private List<Custo> custo;

    @OneToMany
    @JoinColumn(name = "responsavel_negocio_id")
    private List<ResponsavelNegocio> responsavelNegocio;

    @OneToOne
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "ata_id")
    private Ata ata;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proposta")
    private List<Anexo> anexo;

    /**
     * Função para adicionar anexos em uma proposta
     *
     * @param files
     */
    public void setAnexos(List<MultipartFile> files) {
        List<Anexo> listaAnexos = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                listaAnexos.add(new Anexo(file.getOriginalFilename(), file.getContentType(), file.getBytes()));
            }
            this.anexo = listaAnexos;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
