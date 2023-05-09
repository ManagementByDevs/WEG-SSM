describe("Testes de carga, processo e integração no endPoint criar mensagen", () => {
  let mensagem = require("../../fixtures/mensagem.json");

  it("login", () => {
    cy.visit("http://localhost:3000/login");
    cy.visit("http://localhost:3000/", {
        auth: {
            username: "kenzo@gmail",
            password: "123"
        }
    });

    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("LoginRequest");

    cy.get("@LoginRequest").then((response) => {
      console.log("repsonse do teste: ", response);
      expect(response.body).to.be.an("object");
      expect(response.status).to.equal(200);
    });
  });

  it("Criar 500 mensagens", () => {
    for (let i = 0; i < 500; i++) {
      cy.request("POST", "http://localhost:8443/weg_ssm/mensagem", {
        ...mensagem,
        texto: `${mensagem.texto + i}`,
      }).as("MensagemRequest");
      cy.get(
        "@MensagemRequest"
          , {
            
            'auth': {
                "email": "kenzo@gmail",
                "senha": "123456",
                "sendImmediately": false
            }
          }
      ).then((response) => {
        expect(response.body).to.be.an("object");
        expect(response.status).to.equal(200);
      });
    }
  });
});
