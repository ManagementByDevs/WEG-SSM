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

  responsavelNegocio() {
    return {
      id: 0,
      nome: "",
      area: "",
    };
  }

  secaoTI() {
    return { idSecao: 0, nomeSecao: "", siglaSecao: "" };
  }

  forum() {
    return { idForum: 0, nomeForum: "", siglaForum: "" };
  }

  bu() {
    return { idBu: 0, siglaBu: "", nomeBu: "" };
  }

  anexo() {
    return { id: 0, nome: "", tipo: "", dados: "" };
  }

  beneficio() {
    return {
      id: 0,
      tipoBeneficio: "", // "POTENCIAL" | "QUALITATIVO" | "REAL"
      valor_mensal: "",
      moeda: "",
      memoriaCalculo: "",
    };
  }

  proposta() {
    return {
      analista: this.usuario(),
      anexo: [this.anexo()],
      beneficios: [this.beneficio()],
      buSolicitante: this.bu(),
      busBeneficiadas: [this.bu()],
      codigoPPM: 0,
      data: "",
      demanda: {},
      departamento: this.departamento(),
      escopo: 0,
      fimExecucao: "",
      forum: this.forum(),
      frequencia: "",
      gerente: this.usuario(),
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
      responsavelNegocio: [this.responsavelNegocio()],
      secaoTI: this.secaoTI(),
      solicitante: this.usuario(),
      status: "",
      tabelaCustos: [this.tabelaCustos()],
      tamanho: "",
      titulo: "",
      visibilidade: true,
      motivoRecusa: "",
      presenteEm: "", // "Pauta" | "Ata" | "Nada"
    };
  }

  tabelaCustos() {
    return {
      id: 0,
      custos: [this.custo()],
      ccs: [this.cc()],
      tipoDespesa: "Interna",
    };
  }

  custo() {
    return {
      id: 0,
      perfilDespesa: "",
      periodoExecucao: 0,
      horas: 0,
      valorHora: 0,
    };
  }

  cc() {
    return { id: 0, codigo: 0, porcentagem: 0.0 };
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
      score: 0,
      publicada: false,
      publicadaDg: false,
    };
  }

  forum() {
    return { idForum: 0, siglaForum: "", nomeForum: "" };
  }

  chat() {
    return {
      id: 0,
      conversaEncerrada: false,
      idProposta: this.proposta(),
      idDemanda: {},
      usuariosChat: [this.usuario()],
      msgNaoLidas: 0,
    };
  }

  mensagem() {
    return {
      id: 0,
      data: "",
      visto: false,
      texto: "",
      status: "",
      usuario: this.usuario(),
      idChat: this.chat(),
      anexo: this.anexo(),
    };
  }

  preferencias() {
    return {
      themeMode: "",
      fontSizeDefault: "",
      itemsVisualizationMode: "",
      lang: "",
      abaPadrao: "",
    };
  }

  notificacao() {
    return {
      id: 0,
      numeroSequencial: "",
      visualizado: false,
      data: "",
      tipoNotificacao: "",
      usuario: this.usuario(),
      remetente: this.usuario(),
    };
  }
}

export default new EntitiesObjectService();
