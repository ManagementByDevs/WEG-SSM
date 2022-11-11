package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ParecerGerencia {

    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    BUSSINESS_CASE("Business Case"),
    MAIS_INFORMACOES("Mais Informações");

    String parecer;

}
