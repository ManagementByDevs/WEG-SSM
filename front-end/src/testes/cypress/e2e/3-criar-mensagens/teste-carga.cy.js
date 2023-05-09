describe("Teste de carga do endPoint criar mensagen", () => {
  let mensagem = require("../../fixtures/mensagem.json");

  before(() => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("LoginAnalista");
    cy.get("@LoginAnalista");
  });

  it("Criar 300 mensagens", () => {
    for (let i = 0; i < 300; i++) {
      let form = new FormData();
      form.append(
        "mensagem",
        JSON.stringify({ ...mensagem, texto: mensagem.texto + (i + 1) })
      );

      cy.request({
        method: "POST",
        url: "http://localhost:8443/weg_ssm/mensagem",
        body: form,
      }).then((response) => {
        expect(response.status).to.equal(201);
        expect(response.duration).to.be.lessThan(700)
      });
    }
  });
});
