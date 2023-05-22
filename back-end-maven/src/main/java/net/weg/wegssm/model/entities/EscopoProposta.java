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

    /**
     * Data de criação do escopo da proposta
     */
    @Column
    private Date inicioExecucao;

    /**
     * Data de finalização do escopo da proposta
     */
    @Column
    private Date fimExecucao;

    /**
     * Valor do payback do escopo da proposta
     */
    @Column
    private Double paybackValor;

    /**
     * Tipo do payback do escopo da proposta
     */
    @Column(length = 45)
    private String paybackTipo;

    /**
     * Código PPM do escopo da proposta
     */
    @Column
    private Integer codigoPPM;

    /**
     * Link do JIRA do escopo da proposta
     */
    @Column
    private String linkJira;

    /**
     * Título do escopo da proposta
     */
    @Column(length = 100)
    private String titulo;

    /**
     * Problema do escopo da proposta, em bytes pois está em HTML
     */
    @Column
    @Lob
    private byte[] problema;

    /**
     * Proposta do escopo da proposta, em bytes pois está em HTML
     */
    @Column
    @Lob
    private byte[] proposta;

    /**
     * Frequência do escopo da proposta
     */
    @Column(length = 100)
    private String frequencia;

    /**
     * Tamanho do escopo da proposta
     */
    @Column(length = 20)
    private String tamanho;

    /**
     * String para verificar se está presente em uma pauta, ata ou em nenhuma da duas
     */
    @Column
    private String presenteEm;

    /**
     * Data da última modificação do escopo da proposta
     */
    @Column
    private Date ultimaModificacao;

    /**
     * Escopo do escopo da proposta, em bytes pois está em HTML
     */
    @Lob
    @Column
    private byte[] escopo;

    /**
     * Foreign keys
     */

    /**
     * SecaoTI do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false, name = "secao_ti_id")
    private SecaoTI secaoTI;

    /**
     * Lista de benefícios do escopo da proposta
     */
    @OneToMany
    @JoinColumn(name = "escopo_proposta_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    /**
     * BU solicitante do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bu_solicitante")
    private Bu buSolicitante;

    /**
     * Lista de BU's beneficiadas do escopo da proposta
     */
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "bu_escopo_proposta",
            joinColumns = @JoinColumn(name = "escopo_proposta_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id"))
    private List<Bu> busBeneficiadas;

    /**
     * Solicitante do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    /**
     * Gerente do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    /**
     * Analista do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    /**
     * Fórum do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    /**
     * Departamento do escopo da proposta
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    /**
     * Lista de tabelas de custo do escopo da proposta
     */
    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "escopo_proposta_id")
    private List<TabelaCusto> tabelaCustos;

    /**
     * Lista de responsáveis negócio do escopo da proposta
     */
    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "responsavel_negocio_id")
    private List<ResponsavelNegocio> responsavelNegocio;

    /**
     * Demanda do escopo da proposta
     */
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    /**
     * Lista de anexos do escopo da proposta
     */
    @OneToMany
    @JoinColumn(name = "id_escopo_proposta")
    private List<Anexo> anexo;

    /**
     * Função para adicionar anexos em um escopo
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

    /**
     * Função para adicionar novos anexos a uma lista de anexos já existente
     *
     * @param files Lista de anexos novos a serem transformados
     */
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
