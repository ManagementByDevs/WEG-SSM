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
    ASSESSMENT("Assessment"),
    DONE("Done");

    String status;

}
