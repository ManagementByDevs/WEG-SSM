describe("Teste de Integração da busca de propostas", () => {
    before(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginAnalista");
        cy.get("@LoginAnalista");
    })

    it("Buscar Propostas retorna status OK", () => {
        cy.request("GET", "http://localhost:8443/weg_ssm/proposta/page?status=ASSESSMENT_APROVACAO", ).as("TesteBuscarPropostas");
        cy.get("@TesteBuscarPropostas").then((response) => {
            expect(response.status).to.eq(200);
        })
    })
})