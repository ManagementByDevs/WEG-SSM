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

    cy.get("@BuscarMensagens").then((getResponse) => {
      qtdMensagens = getResponse.body.length;
      expect(getResponse.status).to.eq(200);
    });
  });

  it("Criar Mensagem", () => {
    let form = new FormData();
    form.append("mensagem", JSON.stringify(mensagem));

    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).then((authResponse) => {
      expect(authResponse.status).to.eq(200);
      cy.request({
        method: "POST",
        url: "http://localhost:8443/weg_ssm/mensagem",
        body: form,
      }).then((postResponse) => {
        expect(postResponse.status).to.equal(201);
      });
    });
  });

  it("Verificar quantidade de mensagens", () => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).then((authResponse) => {
      expect(authResponse.status).to.eq(200);

      cy.request("GET", "http://localhost:8443/weg_ssm/mensagem")
        .as("BuscarMensagens")
        .then((response) => {
          expect(response.body.length).to.eq(qtdMensagens + 1);
        });
    });
  });
});
