describe("Teste de carga da busca de propostas", () => {
    before(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginAnalista");
        cy.get("@LoginAnalista");
    })

    it("Verificar tempo de resposta após várias requisições", () => {
        for(let i = 0; i < 1000; i++) {
            cy.request("GET", "http://localhost:8443/weg_ssm/proposta/page?status=ASSESSMENT_APROVACAO").as("TesteBuscarPropostas");
            cy.get("@TesteBuscarPropostas").then((response) => {
                expect(response.duration).to.be.lessThan(2000);
            })
        }
    })
})