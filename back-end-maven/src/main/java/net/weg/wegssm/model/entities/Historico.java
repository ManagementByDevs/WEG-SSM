package net.weg.wegssm.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "historico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentoHistorico documentoHistorico;

    @Column(nullable = false , length = 45)
    private String acaoRealizada;

    public void setDocumentoMultipart(MultipartFile file) {
        try {
            this.documentoHistorico = new DocumentoHistorico(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
