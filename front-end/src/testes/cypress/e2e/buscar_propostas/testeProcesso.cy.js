describe("Teste de processo da busca de propostas", () => {
    beforeEach(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginAnalista");
        cy.get("@LoginAnalista")
    })

    let idProposta = null;
    it("Buscar todas as propostas", () => {
        cy.request("GET", "http://localhost:8443/weg_ssm/proposta/page?status=BACKLOG_REVISAO").as("TesteBuscarPropostas");
        cy.get("@TesteBuscarPropostas").then((response) => {
            if(response.body.content[0]) {
                idProposta = response.body.content[0].id;
            } else {
                idProposta = 1;
            }
            expect(response.status).to.eq(200);
        })
    })

    it("Buscar Proposta Individual", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/proposta/${idProposta}`).as("TesteBuscarPropostaIndividual");
        cy.get("@TesteBuscarPropostaIndividual").then((response) => {
            expect(response.status).to.eq(200);
        })
    })
})