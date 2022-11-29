package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoUsuario {

    SOLICITANTE("Solicitante"),
    ANALISTA("Analista"),
    GERENTE("Gerente"),
    GESTOR("Gestor");

    String tipo;
}
