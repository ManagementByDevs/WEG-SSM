let ata = require("../../fixtures/ata.json");

before(() => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).as("LoginAnalista");
  cy.get("@LoginAnalista");
});

// Integração
it("Buscar todas as atas", () => {
  cy.request("GET", "http://localhost:8443/weg_ssm/ata").as("getAta");

  cy.get("@getAta").then((response) => {
    console.log("Atas: ", response.body);
    console.log("Quantidade de atas: ", response.body.length);
    expect(response.status).to.be.equal(200);
  });
});

it("Criar ata errada", () => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).then((authResponse) => {
    cy.request({
      method: "POST",
      url: "http://localhost:8443/weg_ssm/ata",
      body: { ...ata, numeroSequencial: 1 },
      failOnStatusCode: false,
    }).as("postLogin");

    cy.get("@postLogin").then((response) => {
      console.log("Ata: não criada!");
      expect(response.status).to.be.equal(409);
    });
  });
});
