describe("Login", () => {
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
    });
  });

  it("Requisição de proposta sem estar logado", () => {
    cy.request({
      method: "GET",
      url: "http://localhost:8443/weg_ssm/proposta",
      failOnStatusCode: false,
    }).as("getProposta");

    cy.get("@getProposta").then((response) => {
      expect(response.status).to.be.equal(401);
    });
  });

  it("Login com sucesso ", () => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("postLogin");

    cy.get("@postLogin").then((response) => {
      expect(response.status).to.be.equal(200);
    });
  });

  it("Requisição de proposta estando logado", () => {
    cy.request("GET", "http://localhost:8443/weg_ssm/proposta").as(
      "getProposta"
    );

    cy.get("@getProposta").then((response) => {
      expect(response.status).to.be.equal(200);
    });
  });
});