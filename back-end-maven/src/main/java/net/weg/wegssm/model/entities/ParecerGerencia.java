package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ParecerGerencia {

    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    BUSINESS_CASE("Business Case"),
    MAIS_INFORMACOES("Mais Informações");

    String parecer;

}
