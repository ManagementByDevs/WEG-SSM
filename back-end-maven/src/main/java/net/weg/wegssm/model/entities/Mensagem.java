package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mensagem")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Boolean visto;

    @Column(nullable = false, length = 200)
    private String texto;

    @Column()
    private StatusChat status;

    // Foreign keys

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mensagem")
    private List<Anexo> anexo;

    /**
     * Função para adicionar anexos a mensagem
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
