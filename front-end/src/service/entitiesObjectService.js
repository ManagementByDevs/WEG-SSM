class EntitiesObjectService {
  proposta() {
    return {
      analista: {},
      anexo: [{ id: 0, nome: "", tipo: "", dados: "" }],
      beneficios: [
        {
          id: 0,
          tipoBeneficio: "POTENCIAL" | "QUALITATIVO" | "REAL",
          valor_mensal: 0,
          moeda: "",
          memoriaCalculo: "",
        },
      ],
      buSolicitante: { idBu: 0, nomeBu: "", siglaBu: "" },
      busBeneficiadas: [{ idBu: 0, nomeBu: "", siglaBu: "" }],
      codigoPPM: 0,
      data: "",
      demanda: 0,
      departamento: 0,
      escopo: 0,
      fimExecucao: "",
      forum: { idForum: 0, nomeForum: "", siglaForum: "" },
      frequencia: "",
      gerente: 0,
      historicoProposta: [],
      id: 0,
      inicioExecucao: "",
      linkJira: "",
      naoPublicada: true,
      parecerComissao: "",
      parecerDG: "",
      parecerInformacao: "",
      parecerInformacaoDG: "",
      paybackTipo: "",
      paybackValor: 0,
      problema: "",
      proposta: "",
      publicada: false,
      responsavelNegocio: [],
      secaoTI: { idSecao: 0, nomeSecao: "", siglaSecao: "" },
      solicitante: {},
      status: "",
      tabelaCustos: [
        {
          id: 0,
          custos: [
            {
              id: 0,
              tipoDespesa: "",
              perfilDespesa: "",
              periodoExecucao: 0,
              horas: 0,
              valorHora: 0,
            },
          ],
          ccs: [{ id: 0, codigo: 0, porcentegem: 0.0 }],
        },
      ],
      tamanho: "",
      titulo: "",
      visibilidade: true,
    };
  }

  pauta() {
    return {
      analistaResponsavel: {},
      comissao: "",
      dataReuniao: "",
      id: 0,
      numeroSequencial: 0,
      propostas: [this.proposta()],
    };
  }

  ata() {
    return {
      id: 0,
      dataReuniao: "",
      numeroSequencial: "1/2022",
      comissao: this.forum(),
      visibilidade: true,
      propostas: [this.proposta()],
    };
  }

  forum() {
    return { idForum: 0, siglaForum: "", nomeForum: "" };
  }
}

export default new EntitiesObjectService();
