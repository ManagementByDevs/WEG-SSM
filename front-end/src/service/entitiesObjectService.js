class EntitiesObjectService {
  usuario() {
    return {
      id: 0,
      nome: "",
      senha: "",
      email: "",
      visibilidade: true,
      tipoUsuario: "SOLICITANTE" | "ANALISTA" | "GERENTE" | "GESTOR",
      preferencias: "",
      departamento: this.departamento(),
    };
  }

  departamento() {
    return {
      id: 0,
      nome: "",
      visibilidade: true,
    };
  }

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
      emPauta: false,
      emAta: false
    };
  }

  pauta() {
    return {
      analistaResponsavel: {},
      comissao: "",
      dataReuniao: "",
      id: 0,
      numeroSequencial: "",
      propostas: [this.proposta()],
    };
  }

  ata() {
    return {
      id: 0,
      dataReuniao: "",
      numeroSequencial: "",
      comissao: this.forum(),
      visibilidade: true,
      propostas: [this.proposta()],
      analistaResponsavel: this.usuario(),
    };
  }

  forum() {
    return { idForum: 0, siglaForum: "", nomeForum: "" };
  }
}

export default new EntitiesObjectService();
