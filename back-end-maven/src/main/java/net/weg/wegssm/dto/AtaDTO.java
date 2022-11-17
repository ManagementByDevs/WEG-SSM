package net.weg.wegssm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class AtaDTO {

    private java.sql.Date inicioDataReuniao;
    private java.sql.Date fimDataReuniao;
    private String numeroSequencial;

}
