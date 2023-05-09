describe("Login", () => {
  let usuarioLogado;
  let somaTempoResposta = 0;
  let usuario = {
    email: "thiaguin",
    senha: "123456",
  };

  it("Login email incorreto", () => {
    cy.request({
      method: "POST",
      url: "http://localhost:8443/weg_ssm/login/auth",
      body: usuario,
      failOnStatusCode: false,
    }).as("postLogin");

    cy.get("@postLogin").then((response) => {
      expect(response.status).to.be.equal(403);
      cy.request({
        method: "GET",
        url: "http://localhost:8443/weg_ssm/proposta",
        failOnStatusCode: false,
      }).as("getProposta");

      cy.get("@getProposta").then((response) => {
        console.log("Propostas: ", response.body);
        expect(response.status).to.be.equal(401);
      });
    });
  });

  it("Login com sucesso (processo+integração)", () => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("postLogin");

    cy.get("@postLogin").then((response) => {
      usuarioLogado = response.body;
      console.log("Usuario logado: ", usuarioLogado);
      cy.request("GET", "http://localhost:8443/weg_ssm/proposta").as(
        "getProposta"
      );

      cy.get("@getProposta").then((response) => {
        console.log("Propostas: ", response.body);
        expect(response.status).to.be.equal(200);
      });
    });
  });

  it("Teste de carga", () => {
    for (let i = 0; i < 99; i++) {
      cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
        email: "kenzo@gmail",
        senha: "123",
      }).as("login");

      cy.get("@login").then((response) => {
        somaTempoResposta += response.duration;
        if (response.status != 200) {
          console.log("Erro no login: ", response);
          return;
        }
      });
    }
  });

  it("Calcular tempo de resposta do teste de carga", () => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("login");

    cy.get("@login").then((response) => {
      somaTempoResposta += response.duration;
      console.log("Tempo de resposta media: ", somaTempoResposta / 100);
      console.log("Tempo de resposta total: ", somaTempoResposta);
      expect(somaTempoResposta / 100).to.not.be.greaterThan(1000);
    });
  });
});
