package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 200)
    private String problema;

    @Column(nullable = false, length = 200)
    private String proposta;

    @Column(nullable = false, length = 30)
    private String frequencia;

    @Column(length = 20)
    private String tamanho;

    @Column(length = 45)
    private String secaoTI;

    @Column(length = 200)
    private String motivoRecusa;

    @Column(nullable = false)
    private Status status;

    // foreign keys

    @ManyToOne
    @JoinColumn(name = "bu_solicitante")
    private Bu buSolicitante;

    @ManyToMany
    @JoinTable(
            name = "demanda_bu",
            joinColumns = @JoinColumn(name = "demanda_id"),
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_demanda")
    private List<Anexo> anexo;

    // Cadastro de anexos

    /**
     * Função para adicionar anexos em uma demanda
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
