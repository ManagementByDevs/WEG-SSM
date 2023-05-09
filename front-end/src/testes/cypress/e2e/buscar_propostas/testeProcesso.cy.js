describe("Teste de processo da busca de propostas", () => {
    before(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginAnalista");
        cy.get("@LoginAnalista");
    })

    let idProposta = null;
    it("Buscar todas as propostas", () => {
        cy.request("GET", "http://localhost:8443/weg_ssm/proposta/page").as("TesteBuscarPropostas");
        cy.get("@TesteBuscarPropostas").then((response) => {
            idProposta = response.body[0].id;
            expect(response.status).to.eq(200);
        })
    })

    it("Buscar Proposta Individual", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/proposta/${idProposta}`).as("TesteBuscarPropostas");
        cy.get("@TesteBuscarPropostas").then((response) => {
            idProposta = response.body[0].id;
            expect(response.status).to.eq(200);
        })
    })
})