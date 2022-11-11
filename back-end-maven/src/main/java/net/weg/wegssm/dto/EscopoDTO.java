package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Usuario;

import java.util.List;

@Getter @Setter
public class EscopoDTO {

    private Usuario usuario;
    private String titulo;
    private String problema;
    private String proposta;
    private String frequencia;
    private Long porcentagem;
    private List<Anexo> anexo;

}
