package net.weg.wegssm.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**
     * Data de quando a mensagem foi enviada
     */
    @Column(nullable = false)
    private Date data;

    /**
     * Verificação caso a mensagem seja vista
     */
    @Column(nullable = false)
    private Boolean visto;

    /**
     * Texto da mensagem
     */
    @Column(nullable = false, length = 200)
    private String texto;

    /**
     * Status da mensagem
     */
    @Column
    private StatusChat status;

    /**
     * Foreign keys
     */

    /**
     * Usuário do chat
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /**
     * ID do chat que pertence a mensagem
     */
    @ManyToOne
    @JoinColumn(name = "id_chat", nullable = false)
//    @JsonIgnore
    private Chat idChat;

    /**
     * Anexos enviados na mensagem
     */
    @OneToOne
    @JoinColumn(name = "id_anexo")
    private Anexo anexo;

}
