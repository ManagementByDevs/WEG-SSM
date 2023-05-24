package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "demanda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    /**
     * Título da demanda
     */
    @Column(nullable = false, length = 100)
    private String titulo;

    /**
     * Problema da demanda, em bytes pois está em HTML
     */
    @Column(nullable = false)
    @Lob
    private byte[] problema;

    /**
     * Proposta da demanda, em bytes pois está em HTML
     */
    @Column(nullable = false)
    @Lob
    private byte[] proposta;

    /**
     * Frequência da demanda
     */
    @Column(nullable = false, length = 100)
    private String frequencia;

    /**
     * Tamanho da demanda
     */
    @Column(length = 20)
    private String tamanho;

    /**
     * Motivo da recusa da demanda
     */
    @Column(length = 1000)
    private String motivoRecusa;

    /**
     * Status da demanda
     */
    @Column(nullable = false)
    private Status status;

    /**
     * Score da demanda
     */
    @Column(nullable = false)
    private Double score;

    /**
     * Data de criação da demanda
     */
    @Column(nullable = false)
    private Date data;

    /**
     * Foreign keys
     */

    /**
     * Seção de TI que pertence a demanda
     */
    @ManyToOne
    @JoinColumn(name = "secao_ti_id")
    private SecaoTI secaoTI;

    /**
     * Lista de benefícios que a demanda possui
     */
    @OneToMany
    @JoinColumn(name = "demanda_id")
    private List<Beneficio> beneficios = new ArrayList<>();

    /**
     * BU solicitante da demanda
     */
    @ManyToOne
    @JoinColumn(name = "bu_solicitante")
    private Bu buSolicitante;

    /**
     * Lista de BU's beneficiadas da demanda
     */
    @ManyToMany
    @JoinTable(
            name = "demanda_bu",
            joinColumns = @JoinColumn(name = "demanda_id"),
            inverseJoinColumns = @JoinColumn(name = "bu_id"))
    private List<Bu> busBeneficiadas;

    /**
     * Solicitante da demanda
     */
    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    /**
     * Gerente da demanda
     */
    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Usuario gerente;

    /**
     * Analista da demanda
     */
    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    /**
     * Fórum da demanda
     */
    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    /**
     * Departamento da demanda
     */
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    /**
     * Lista de anexos que a demanda possui
     */
    @OneToMany
    @JoinColumn(name = "id_demanda")
    private List<Anexo> anexo;

    /**
     * Histórico de modificações da demanda
     */
    @OneToMany
    @JoinColumn(name = "demanda_id")
    private List<Historico> historicoDemanda;

    /**
     * Função para adicionar anexos em uma demanda
     *
     * @param files Lista de arquivos a serem transformados em anexos
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
     * @param files       Lista de anexos novos a serem transformados
     * @param listaAnexos Lista de anexos já existente
     */
    public void addAnexos(List<MultipartFile> files, List<Anexo> listaAnexos) {
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
     * Função para setar os anexos da demanda diretamente através de uma lista de Anexos
     *
     * @param files Lista de anexos
     */
    public void setAnexosWithoutMultiparFile(List<Anexo> files) {
        this.anexo = files;
    }

}
