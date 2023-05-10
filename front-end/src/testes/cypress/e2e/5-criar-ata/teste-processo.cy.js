let ata = require("../../fixtures/ata.json");
let quantidadeAtas = 0;
let somaTempoResposta = 0;
let idAta = 0;
let listaIdAtas = [];

before(() => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).as("LoginAnalista");
  cy.get("@LoginAnalista");
});

it("Buscar todas as atas", () => {
  cy.request("GET", "http://localhost:8443/weg_ssm/ata").as("getAta");

  cy.get("@getAta").then((response) => {
    console.log("Atas: ", response.body);
    console.log("Quantidade de atas: ", response.body.length);
    expect(response.status).to.be.equal(200);
    quantidadeAtas = response.body.length;
  });
});

it("Criar ata", () => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).then((authResponse) => {
    cy.request("POST", "http://localhost:8443/weg_ssm/ata", ata).as("postAta");

    cy.get("@postAta").then((responsePost) => {
      console.log("Ata: ", responsePost.body);
      expect(responsePost.status).to.be.equal(200);
    });
  });
});

it("Verificar quantidade de atas", () => {
  cy.request("GET", "http://localhost:8443/weg_ssm/ata").as("getAta");

  cy.get("@getAta").then((response) => {
    console.log("Quantidade de atas depois: ", response.body.length);
    idAta = responsePost.body.id;
    expect(response.status).to.be.equal(200);
    expect(response.body.length).to.be.equal(quantidadeAtas + 1);
  });
});

it("Deletar ata criada", () => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).then((authResponse) => {
    cy.request("DELETE", `http://localhost:8443/weg_ssm/ata/${idAta}`).as(
      "deleteAta"
    );

    cy.get("@deleteAta").then((response) => {
      console.log("Ata: ", response.body);
      expect(response.status).to.be.equal(200);
    });
  });
});

it("Verificar quantidade de atas", () => {
  cy.request("GET", "http://localhost:8443/weg_ssm/ata").as("getAta");

  cy.get("@getAta").then((response) => {
    console.log("Quantidade de atas depois: ", response.body.length);
    idAta = responsePost.body.id;
    expect(response.status).to.be.equal(200);
    expect(response.body.length).to.be.equal(quantidadeAtas);
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

it("Verificar quantidade de atas", () => {
  cy.request("GET", "http://localhost:8443/weg_ssm/ata").as("getAta");

  cy.get("@getAta").then((response) => {
    console.log("Quantidade de atas depois: ", response.body.length);
    idAta = responsePost.body.id;
    expect(response.status).to.be.equal(200);
    expect(response.body.length).to.be.equal(quantidadeAtas);
  });
});

