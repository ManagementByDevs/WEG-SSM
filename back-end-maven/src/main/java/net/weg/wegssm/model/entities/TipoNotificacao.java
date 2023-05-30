package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoNotificacao {

    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    MAIS_INFORMACOES("Mais Informações"),
    MENSAGENS("Mensagens"),
    APROVADO_GERENTE("Aprovado Gerente"),
    REPROVADO_GERENTE("Reprovado Gerente"),
    CRIADO_PROPOSTA("Criado Proposta"),
    APROVADO_COMISSAO("Aprovado Comissão"),
    REPROVADO_COMISSAO("Reprovado Comissão"),
    BUSINESS_CASE_COMISSAO("Business Case Comissão"),
    MAIS_INFORMACOES_COMISSAO("Mais Informações Comissão"),
    APROVADO_DG("Aprovado DG"),
    REPROVADO_DG("Reprovado DG");

    String tipo;
}
