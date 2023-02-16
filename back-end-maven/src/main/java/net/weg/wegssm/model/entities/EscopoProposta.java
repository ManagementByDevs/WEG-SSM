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

    @Column(length = 2000)
    private String problema;

    @Column(length = 2000)
    private String proposta;

    @Column(length = 100)
    private String frequencia;

    @Column(length = 20)
    private String tamanho;

    @Column(length = 45)
    private String secaoTI;

    @Column
    private Date ultimaModificacao;

    @Lob
    @Column
    private byte[] escopo;

//    Foreign Keys

    @OneToMany
    @JoinColumn(name = "escopo_proposta_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bu_solicitante")
    private Bu buSolicitante;

    @ManyToMany
    @JoinTable(
            name = "bu_escopo_proposta",
            joinColumns = @JoinColumn(name = "escopo_proposta_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id"))
    private List<Bu> busBeneficiadas;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany
    @JoinColumn(name = "escopo_proposta_id")
    private List<TabelaCusto> tabelaCustos;

    @OneToMany
    @JoinColumn(name = "responsavel_negocio_id")
    private List<ResponsavelNegocio> responsavelNegocio;

    @OneToOne
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_escopo_proposta")
    private List<Anexo> anexo;

    /**
     * Função para adicionar anexos em um escopo
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

    public void addAnexos(List<MultipartFile> files, List<Anexo> listaAnexos) {
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
