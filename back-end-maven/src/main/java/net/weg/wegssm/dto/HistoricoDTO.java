package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.DocumentoHistorico;
import net.weg.wegssm.model.entities.Usuario;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class HistoricoDTO {

    private Date data;

    @NotNull
    private String acaoRealizada;

    private DocumentoHistorico documentoHistorico;

    @NotNull
    private Usuario autor;

}
