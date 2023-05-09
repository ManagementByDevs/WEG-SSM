
describe("Cadastro de Demanda - Integração", () => {
    beforeEach(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginRequest");

        cy.get("@LoginRequest");
    })

    it("Verifica se o cadastro de demanda foi efetuado", () => {
        let demanda = {
            titulo: "titulo100",
            problema: "problema",
            proposta: "proposta",
            frequencia: "frequencia",
            status: "BACKLOG_REVISAO",
        }

        cy.request({
            method: "POST",
            url: `http://localhost:8443/weg_ssm/demanda/sem-arquivos/3?demanda=${JSON.stringify(demanda)}`
        }).as("CadastroDemandaRequest");

        cy.get("@CadastroDemandaRequest").then(response => {
            expect(response.duration).to.be.lessThan(2000)
            expect(response.body).to.be.an("object")
            expect(response.status).to.equal(200)
        })
    })
})

