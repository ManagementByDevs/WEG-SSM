package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

/**
 * Classe ENUM para definir os tipos de usuário do sistema
 */
@AllArgsConstructor
public enum TipoUsuario {

    SOLICITANTE("Solicitante"),
    ANALISTA("Analista"),
    GERENTE("Gerente"),
    GESTOR("Gestor");

    String tipo;

}
