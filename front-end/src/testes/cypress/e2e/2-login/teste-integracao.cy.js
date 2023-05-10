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

  it("Login com sucesso ", () => {
    cy.request("POST", "http://localhost:8443/weg_ssm/login/auth", {
      email: "kenzo@gmail",
      senha: "123",
    }).as("postLogin");

    cy.get("@postLogin").then((response) => {
      expect(response.status).to.be.equal(200);
    });
  });
});