package net.weg.wegssm.dto;

import lombok.Data;
import net.weg.wegssm.model.entities.DocumentoHistorico;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class HistoricoDTO {

    private Date data;

    @NotNull
    private String acaoRealizada;

    private DocumentoHistorico documentoHistorico;

}
