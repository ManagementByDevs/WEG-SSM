package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Usuario;

import java.util.List;

/**
 * Classe DTO para a criação de um escopo
 */
@Data
public class EscopoDTO {

    private String titulo;
    private byte[] problema;
    private byte[] proposta;
    private String frequencia;
    private Long porcentagem;
    private Usuario usuario;
    private List<Anexo> anexo;

}
