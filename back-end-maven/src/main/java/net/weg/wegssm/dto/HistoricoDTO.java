package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.DocumentoHistorico;
import net.weg.wegssm.model.entities.Usuario;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
public class HistoricoDTO {

    private Date data;
    private String acaoRealizada;

}
