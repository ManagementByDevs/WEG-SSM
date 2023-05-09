describe("Teste de integração do endPoint criar mensagen", () => {
  before(() => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("LoginAnalista");
    cy.get("@LoginAnalista");
  });

  it("Buscar todas as mensagens", () => {
    cy.request("GET", "http://localhost:8443/weg_ssm/mensagem").as(
      "BuscarMensagens"
    );
    cy.get("@BuscarMensagens").then((response) => {
      expect(response.status).to.eq(200);
    });
  });
});
