
describe("Cadastro de Demanda - Integração", () => {
    beforeEach(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginRequest");

        cy.get("@LoginRequest");
    })

    it("Verifica se o cadastro de demanda foi efetuado", () => {
        cy.request({
            method: "POST",
            url: "http://localhost:8443/weg_ssm/demanda",
            body: {
                titulo: "titulo",
                problema: "problema",
                proposta: "proposta",
                frequencia: "frequencia",
                status: "BACKLOG_REVISAO",
                tamannho: null,
                secaoTI: null,
                motivoRecusa: null,
                solicitante: null,
                forum: null,
                departamento: null,
                beneficios: null
            }
        }).as("CadastroDemandaRequest");

        cy.get("@CadastroDemandaRequest").then(response => {
            expect(response.body).to.be.an("object")
            expect(response.status).to.equal(200)
        })
    })
})

