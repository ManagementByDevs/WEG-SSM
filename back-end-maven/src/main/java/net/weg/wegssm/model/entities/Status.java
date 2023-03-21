package net.weg.wegssm.model.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {

    BACKLOG_REVISAO("Backlog Revisão"),
    BACKLOG_EDICAO("Backlog Edição"),
    BACKLOG_APROVACAO("Backlog Aprovação"),
    CANCELLED("Cancelled"),
    TO_DO("To_Do"),
    BUSINESS_CASE("Business_Case"),
    ASSESSMENT("Assessment Criação"),
    ASSESSMENT_APROVACAO("Assessment Aprovação"),
    ASSESSMENT_EDICAO("Assessment Edição"),
    ASSESSMENT_COMISSAO("Assessment Comissão"),
    ASSESSMENT_DG("Assessment DG"),
    DONE("Done");

    String status;

}
