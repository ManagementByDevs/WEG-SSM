describe("Login", () => {
  let somaTempoResposta = 0;

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
      expect(somaTempoResposta / 100).to.not.be.greaterThan(500);
    });
  });
});
