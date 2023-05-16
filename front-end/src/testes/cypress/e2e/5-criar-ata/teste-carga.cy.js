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

it("Criar atas", () => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).then((authResponse) => {
    for (let i = 2000; i < 2049; i++) {
      cy.request("POST", "http://localhost:8443/weg_ssm/ata", {
        ...ata,
        numeroSequencial: i,
      }).as("postAta");

      cy.get("@postAta").then((response) => {
        somaTempoResposta += response.duration;
        if (response.status == 200) {
          listaIdAtas.push(response.body.id);
        } else {
          console.log("Erro na criação: ", response);
          return;
        }
      });
    }
  });
});

it("Calcular tempo de resposta do teste de carga", () => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).then((authResponse) => {
    cy.request("POST", "http://localhost:8443/weg_ssm/ata", {
      ...ata,
      numeroSequencial: 102,
    }).as("login");

    cy.get("@login").then((response) => {
      somaTempoResposta += response.duration;
      listaIdAtas.push(response.body.id);
      console.log("Tempo de resposta media: ", somaTempoResposta / 100);
      console.log("Tempo de resposta total: ", somaTempoResposta);
      expect(somaTempoResposta / 100).to.not.be.greaterThan(300);
    });
  });
});

it("Deletar atas teste carga", () => {
  cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
    email: "kenzo@gmail",
    senha: "123",
  }).then((authResponse) => {
    for (let i = 0; i < listaIdAtas.length; i++) {
      cy.request("DELETE", `http://localhost:8443/weg_ssm/ata/${listaIdAtas[i]}`).as(
        "deleteAta"
      );

      cy.get("@deleteAta").then((response) => {
        expect(response.status).to.be.equal(200);
      });
    }
  });
});

