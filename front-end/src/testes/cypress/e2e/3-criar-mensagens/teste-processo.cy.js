describe("Teste de processo do endPoint criar mensagen", () => {
  let mensagem = require("../../fixtures/mensagem.json");

  before(() => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("LoginAnalista");
    cy.get("@LoginAnalista");
  });

  let qtdMensagens;

  it("Buscar mensagens", () => {
    cy.request("GET", "http://localhost:8443/weg_ssm/mensagem").as(
      "BuscarMensagens"
    );
    cy.get("@BuscarMensagens").then((response) => {
      qtdMensagens = response.body.length;
      expect(response.status).to.eq(200);
    });
  });

  it("Criar mensagem", () => {
    let form = new FormData();

    form.append("mensagem", JSON.stringify(mensagem));

    cy.request({
      method: "POST",
      url: "http://localhost:8443/weg_ssm/mensagem",
      body: form,
    }).then((response) => {
      expect(response.status).to.equal(200);
    });
  });

  it("Verificar quantidade de menesagens", () => {
    cy.request("GET", "http://localhost:8443/weg_ssm/mensagem").as(
      "BuscarMensagens"
    );
    cy.get("@BuscarMensagens").then((response) => {
      expect(response.body).to.eq(qtdMensagens + 1);
    });
  });
});
