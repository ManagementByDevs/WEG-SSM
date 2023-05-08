describe("Cadastro de Demanada - Integração", () => {
    it("Verifica se o cadastro de demanda foi efetuado", () => {
        cy.request("POST", "http://localhost:8081/demanda", {rua: "oi", numero: 201, cidade: "Jaragua", estado: "SC", bairro: "Jaragua esquerdo", cep: 11111111}).as("EnderecoRequest");
        cy.get("@EnderecoRequest").then(response => {
            expect(response.body).to.be.an("object");
            expect(response.status).to.equal(200)
        })
    })
})