package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "escopo_proposta")
@AllArgsConstructor
@NoArgsConstructor()
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EscopoProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date inicioExecucao;

    @Column
    private Date fimExecucao;

    @Column
    private Double paybackValor;

    @Column(length = 45)
    private String paybackTipo;

    @Column
    private Integer codigoPPM;

    @Column
    private String linkJira;

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

    @Column(length = 20)
    private String tamanho;

    @Column
    private String presenteEm;

    @Column
    private Date ultimaModificacao;

    @Lob
    @Column
    private byte[] escopo;

//    Foreign Keys

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false, name = "secao_ti_id")
    private SecaoTI secaoTI;

    @OneToMany
    @JoinColumn(name = "escopo_proposta_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bu_solicitante")
    private Bu buSolicitante;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "bu_escopo_proposta",
            joinColumns = @JoinColumn(name = "escopo_proposta_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id"))
    private List<Bu> busBeneficiadas;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "escopo_proposta_id")
    private List<TabelaCusto> tabelaCustos;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "responsavel_negocio_id")
    private List<ResponsavelNegocio> responsavelNegocio;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    @OneToMany
    @JoinColumn(name = "id_escopo_proposta")
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

    public void addAnexos(List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                this.anexo.add(new Anexo(null, file.getOriginalFilename(), file.getContentType(), file.getBytes()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
