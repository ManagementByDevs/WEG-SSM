describe("Teste de processo da busca de propostas", () => {
    beforeEach(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginAnalista");
        cy.get("@LoginAnalista")
    })

    let idProposta = null;
    it("Buscar todas as propostas", () => {
        cy.request("GET", "http://localhost:8443/weg_ssm/proposta/page?status=ASSESSMENT_APROVACAO").as("TesteBuscarPropostas");
        cy.get("@TesteBuscarPropostas").then((response) => {
            expect(response.body.content[0]).to.be.an("object");
            if (response.body.content[0]) {
                idProposta = response.body.content[0].id;
            }
            expect(response.status).to.eq(200);
        })
    })

    let solicitanteId = null;
    it("Buscar Proposta Individual", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/proposta/${idProposta}`).as("TesteBuscarPropostaIndividual");
        cy.get("@TesteBuscarPropostaIndividual").then((response) => {
            if (response.body.solicitante.id) {
                solicitanteId = response.body.solicitante.id;
            }
            expect(response.body.id).to.eq(idProposta);
        })
    })

    let solicitante = null;
    it("Buscar Solicitante da Proposta", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/usuario/${solicitanteId}`).as("TesteBuscarSolicitante");
        cy.get("@TesteBuscarSolicitante").then((response) => {
            solicitante = response.body;
            expect(response.status).to.eq(200);
        })
    })

    it("Buscar Propostas do Solicitante", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/proposta/page?status=ASSESSMENT_APROVACAO&solicitante=${JSON.stringify(solicitante)}`).as("TesteBuscarPropostasSolicitante");
        cy.get("@TesteBuscarPropostasSolicitante").then((response) => {
            expect(response.body.content).to.be.an("array");
        })
    })
})