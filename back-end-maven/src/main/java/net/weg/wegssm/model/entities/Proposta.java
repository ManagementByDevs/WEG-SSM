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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Data de início da proposta
     */
    @Column(nullable = false)
    private Date inicioExecucao;

    /**
     * Data de fim da proposta
     */
    @Column(nullable = false)
    private Date fimExecucao;

    /**
     * Valor do payback da proposta
     */
    @Column(nullable = false)
    private Double paybackValor;

    /**
     * Tipo do payback da proposta
     */
    @Column(nullable = false, length = 45)
    private String paybackTipo;

    /**
     * Código PPM da proposta
     */
    @Column(nullable = false, unique = true)
    private Long codigoPPM;

    /**
     * Link do Jira da proposta
     */
    @Column(nullable = false, length = 100)
    private String linkJira;

    /**
     * Verificação se a ata será PUBLICADA ou NÃO PUBLICADA
     */
    @Column
    private Boolean publicada;

    /**
     * Status da proposta
     */
    @Column(nullable = false)
    private Status status;

    /**
     * Score da demanda
     */
    @Column(nullable = false)
    private Double score;

    /**
     * Data de criação da proposta
     */
    @Column
    private Date data;

    /**
     * Parecer da comissão sobre a proposta
     */
    @Column
    private ParecerGerencia parecerComissao;

    /**
     * Informações do parecer da comissão sobre a proposta
     */
    @Column(length = 999999999)
    private String parecerInformacao;

    /**
     * Parecer da DG sobre a proposta
     */
    @Column
    private ParecerGerencia parecerDG;

    /**
     * Informações do parecer da DG sobre a proposta
     */
    @Column(length = 999999999)
    private String parecerInformacaoDG;

    /**
     * Verificação para não excluir a proposta direto do BD
     */
    @Column
    private Boolean visibilidade;

    /**
     * Título da proposta
     */
    @Column(nullable = false, length = 100)
    private String titulo;

    /**
     * Problema da proposta, em byte pois é HTML
     */
    @Column(nullable = false, length = 999999999)
    private String problema;

    /**
     * Proposta da proposta, em byte pois é HTML
     */
    @Column(nullable = false, length = 999999999)
    private String proposta;

    /**
     * Frequência da proposta
     */
    @Column(nullable = false, length = 200)
    private String frequencia;

    /**
     * Tamanho da proposta
     */
    @Column(nullable = false, length = 20)
    private String tamanho;

    /**
     * Motivo da recusa da proposta
     */
    @Column(length = 1000)
    private String motivoRecusa;

    /**
     * String para saber se está em uma pauta, ata ou não está em nenhuma
     */
    @Column(nullable = false)
    private String presenteEm;

    /**
     * Escopo da proposta, em byte pois é HTML
     */
    @Column(length = 999999999)
    private String escopo;

    /**
     * Foreign keys
     */

    /**
     * Seção TI da proposta
     */
    @ManyToOne
    @JoinColumn(nullable = false, name = "secao_ti_id")
    private SecaoTI secaoTI;

    /**
     * Lista de benefícios da proposta
     */
    @OneToMany
    @JoinColumn(name = "proposta_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    /**
     * BU Solicitante da proposta
     */
    @ManyToOne
    @JoinColumn(name = "bu_solicitante")
    private Bu buSolicitante;

    /**
     * Lista de BU's beneficiadas da proposta
     */
    @ManyToMany
    @JoinTable(
            name = "proposta_bu",
            joinColumns = @JoinColumn(name = "proposta_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id"))
    private List<Bu> busBeneficiadas;

    /**
     * Solicitante da proposta
     */
    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    /**
     * Gerente da proposta
     */
    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    /**
     * Analista da proposta
     */
    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    /**
     * Fórum da proposta
     */
    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    /**
     * Departamento da proposta
     */
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    /**
     * Lista de tabelas de custo da proposta
     */
    @OneToMany
    @JoinColumn(name = "proposta_id")
    private List<TabelaCusto> tabelaCustos;

    /**
     * Lista de responsáveis de negócio da proposta
     */
    @ManyToMany
    @JoinTable(
            name = "proposta_responsavel_negocio",
            joinColumns = @JoinColumn(name = "proposta_id"),
            inverseJoinColumns = @JoinColumn(name = "responsavel_negocio_id"))
    private List<ResponsavelNegocio> responsavelNegocio;

    /**
     * Demanda da proposta
     */
    @OneToOne
    @JoinColumn(name = "demanda_id")
    private Demanda demanda;

    /**
     * Lista de anexos da proposta
     */
    @OneToMany
    @JoinColumn(name = "id_proposta")
    private List<Anexo> anexo;

    /**
     * Lista de históricos da proposta
     */
    @OneToMany
    @JoinColumn(name = "proposta_id")
    private List<Historico> historicoProposta;

    /**
     * Função para adicionar anexos em uma proposta
     *
     * @param files Lista de arquivos
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
     * Função para verificar se contém uma tabela de custos na proposta
     *
     * @param tabelaCusto
     * @return
     */
    public boolean containsTabelaCusto(TabelaCusto tabelaCusto) {
        for (TabelaCusto oldTabelaCusto : tabelaCustos) {
            if (oldTabelaCusto.getId().equals(tabelaCusto.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Função para verificar se existem anexos em uma proposta
     *
     * @param anexo
     * @return
     */
    public boolean containsAnexo(Anexo anexo) {
        for (Anexo oldAnexo : this.anexo) {
            if (oldAnexo.getId().equals(anexo.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Função para verificar se contém um custo na tabela de custos da proposta
     *
     * @param oldCusto
     * @param tabelasCusto
     * @return
     */
    public boolean containsCustoInTabelaCusto(Custo oldCusto, List<TabelaCusto> tabelasCusto) {
        for (TabelaCusto tabelaCusto : tabelasCusto) {
            for (Custo custo : tabelaCusto.getCustos()) {
                if (custo.getId().equals(oldCusto.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Função para verificar se existem CCs em uma tabela de custos da proposta
     *
     * @param oldCc
     * @param tabelasCusto
     * @return
     */
    public boolean containsCcInTabelaCusto(CC oldCc, List<TabelaCusto> tabelasCusto) {
        for (TabelaCusto tabelaCusto : tabelasCusto) {
            for (CC cc : tabelaCusto.getCcs()) {
                if (cc.getId().equals(oldCc.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

}
