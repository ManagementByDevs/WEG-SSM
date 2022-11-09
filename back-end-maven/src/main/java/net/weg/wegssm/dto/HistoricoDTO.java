package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.DocumentoHistorico;
import net.weg.wegssm.model.entities.Usuario;

import java.util.Date;

@Getter @Setter
public class HistoricoDTO {

    private Long numeroVersao;
    private Date data;
    private Usuario autor;
    private DocumentoHistorico documento;
    private String acaoRealizada;
    private Demanda demanda;

}
