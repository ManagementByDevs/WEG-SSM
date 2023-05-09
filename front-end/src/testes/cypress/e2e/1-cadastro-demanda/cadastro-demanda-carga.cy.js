describe("Cadastro de Demanda - Carga", () => {
    it("login", () => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginRequest");

        cy.get("@LoginRequest").then((response) => {
            expect(response.body).to.be.an("object");
            expect(response.status).to.equal(200);
        });
    })

    it("Criando 100 demandas", () => {
        for (let i = 0; i <= 100; i++) {
            cy.request({
                method: "POST",
                url: "http://localhost:8443/weg_ssm/demanda",
                body: {
                    titulo: "titulo " + i,
                    problema: "problema " + i,
                    proposta: "proposta " + i,
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
        }
    })
})

