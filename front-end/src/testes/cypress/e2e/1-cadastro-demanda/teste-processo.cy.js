
describe("Cadastro de Demanda - Processo", () => {
    beforeEach(() => {
        cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", { email: "kenzo@gmail", senha: "123" }).as("LoginRequest");

        cy.get("@LoginRequest");
    })

    let listaDemandas = [];

    it("Verifica se existem demandas com status: CANCELLED", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/demanda/status?status=${"CANCELLED"}`).as("GetStatusRequest");
               
        cy.get("@GetStatusRequest").then(response => {
            expect(response.duration).to.be.lessThan(2000)
            expect(response.body).to.be.an("array")
            expect(response.status).to.equal(200)
            listaDemandas = response.body;
        })
    })

    it("Executa um cadastro de demanda com status cancelled", () => {
        let demanda = {
            titulo: "titulo",
            problema: "problema",
            proposta: "proposta",
            frequencia: "frequencia",
            status: "CANCELLED",
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

    it("Verifica novamente as demandas com status: CANCELLED", () => {
        cy.request("GET", `http://localhost:8443/weg_ssm/demanda/status?status=${"CANCELLED"}`).as("GetStatusRequest");

        cy.get("@GetStatusRequest").then(response => {
            expect(response.duration).to.be.lessThan(2000)
            expect(response.body).to.be.an("array")
            expect(response.status).to.equal(200)
            expect(response.body.length).to.be.greaterThan(listaDemandas.length)
        })
    })
})

