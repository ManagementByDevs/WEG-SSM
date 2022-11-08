package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoNotificacao {

    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    MAIS_INFORMACOES("Mais Informações"),
    MENSAGENS("Mensagens");

    String tipo;
}
