package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

/**
 * Classe ENUM para definir os tipos de benefícios
 */
@AllArgsConstructor
public enum TipoBeneficio {

    POTENCIAL("Potencial"),
    QUALITATIVO("Qualitativo"),
    REAL("Real");

    String tipoBeneficio;

}
