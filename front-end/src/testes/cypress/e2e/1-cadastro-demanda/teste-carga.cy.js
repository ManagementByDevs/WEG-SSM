describe("Teste de Carga - Criação Demanda", () => {
    beforeEach(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginRequest");

        cy.get("@LoginRequest");
    })

    it("Criar 500 demandas", () => {
        let demanda = {
            titulo: "titulo",
            problema: "problema",
            proposta: "proposta",
            frequencia: "frequencia",
            status: "BACKLOG_REVISAO",
        }

        for (let i = 0; i <= 50; i++) {
            cy.request({
                method: "POST",
                url: `http://localhost:8443/weg_ssm/demanda/sem-arquivos/3?demanda=${JSON.stringify(demanda)}`
            }).as("CadastroDemandaRequest");

            cy.get("@CadastroDemandaRequest").then(response => {
                expect(response.duration).to.be.lessThan(2000)
                expect(response.body).to.be.an("object")
                expect(response.status).to.equal(200)
            })
        }
    });
});