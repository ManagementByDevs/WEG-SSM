import { createContext } from "react";

const TextLanguageContext = createContext({
  texts: {
    linguagem: "pt",
    ajuda: {
      ajuda: "Ajuda",
    },
    barraProgressaoDemanda: {
      steps: {
        dados: "Dados",
        beneficios: "Benefícios",
        anexos: "Anexos",
      },
      erroPularPasso: "Você não pode pular um passo que não é opcional!",
      botaoVoltar: "Voltar",
      botaoPular: "Pular",
      botaoCriar: "Criar",
      botaoProximo: "Próximo",
      mensagemFeedback: "Preencha todos os campos obrigatórios!",
    },
    barraProgressaoProposta: {
      proposta: "Proposta",
      escopo: "Escopo",
      custo: "Custos",
      gerais: "Gerais",
      botaoVoltar: "Voltar",
      botaoCriar: "Criar",
      botaoProximo: "Próximo",
      mensagemFeedbackCamposObrigatorios:
        "Preencha todos os campos obrigatórios!",
      mensagemFeedbackCcsFaltando:
        "A porcentagem deve fechar 100% em cada CCs!",
    },
    beneficios: {
      beneficios: "Benefícios",
      real: "Real",
      potencial: "Potencial",
      qualitativo: "Qualitativo",
      valorMensal: "Valor Mensal",
      exemploValorMensal: "Ex: 1000,00",
      moeda: "Moeda",
      memoriaCalculo: "Memória de cálculo",
      digiteMemoriaCalculo: "Digite a memória de cálculo...",
    },
    BeneficiosDetalheDemanda: {
      tipo: "Tipo",
      valorMensal: "Valor Mensal",
      moeda: "Moeda",
      memoriaCalculo: "Memória de Cálculo",
      real: "Real",
      potencial: "Potencial",
      qualitativo: "Qualitativo",
      digiteValorMensal: "Digite o valor mensal...",
      digiteMemoriaCalculo: "Digite a memória de cálculo...",
    },
    caminho: {
      home: "Home",
    },
    chatMinimizado: {
      minimizar: "Minimizar",
      maximazar: "Maximizar",
      telaCheia: "Tela Cheia",
      fecharChat: "Fechar Chat",
      escrevaSuaMensagem: "Escreva sua mensagem...",
      enviarAnexo: "Enviar Anexo",
      enviarMensagem: "Enviar Mensagem",
    },
    containerPauta: {
      propostas: "Propostas",
    },
    contato: {
      reabrirChat: "Abrir Chat",
      ppm: "PPM",
      demanda: "Demanda",
    },
    custos: {
      excluirTabelaDeCustos: "Excluir tabela de custos",
      tipoDaDespesa: "Tipo da Despesa",
      perfilDaDespesa: "Perfil da Despesa",
      periodoDeExecucao: "Período de Execução (Meses)",
      horas: "Horas",
      valorHora: "Valor Hora",
      total: "Total",
      h: "h",
      moeda: "R$",
      adicionarNovaLinha: "Adicionar nova linha",
      ccs: "CCs",
    },
    demanda: {
      status: {
        reprovada: "Reprovada",
        aguardandoRevisao: "Aguardando Revisão",
        aguardandoEdicao: "Aguardando Edição",
        emAprovacao: "Em Aprovação",
        aprovada: "Aprovada",
        emAndamento: "Em Andamento",
      },
      motivo: "Motivo",
    },
    demandaGerencia: {
      backlog: "Backlog",
      assessment: "Assessment",
      ppm: "PPM",
      solicitante: "Solicitante",
      departamento: "Departamento",
      naoAtribuido: "Não Atribuído",
      analistaResponsavel: "Analista Responsável",
      gerenteResponsavel: "Gerente Responsável",
      chat: "Chat",
      historico: "Histórico",
      emPauta: "Em Pauta!",
      emAta: "Em Ata!",
    },
    demandaGerenciaModoVisualizacao: {
      backlog: "Backlog",
      assessment: "Assessment",
      codigo: "Código",
      titulo: "Título",
      solicitante: "Solicitante",
      departamento: "Departamento",
      gerenteResponsavel: "Gerente Responsável",
      status: "Status",
      data: "Data",
      naoAtribuido: "Não atribuído",
      historico: "Histórico",
      chat: "Chat",
      proposta: "proposta",
      nadaEncontrado: "Nada encontrado",
      tenteNovamenteMaisTarde: "Tente novamente mais tarde",
    },
    demandaModoVisualizacao: {
      statusDemanda: {
        reprovada: "Reprovada",
        aguardandoRevisao: "Aguardando Revisão",
        aguardandoEdicao: "Aguardando Edição",
        emAprovacao: "Em Aprovação",
        aprovada: "Aprovada",
        emAndamento: "Em Andamento",
      },
      codigo: "Código",
      titulo: "Titulo",
      statusString: "Status",
      data: "Data",
      motivo: "Motivo",
      nadaEncontrado: "Nada encontrado",
      tenteNovamenteMaisTarde: "Tente novamente mais tarde",
    },
    DetalhesDemanda: {
      real: "Real",
      facaAlgumaAlteracaoParaPoderSalvar:
        "Faça alguma alteração para poder salvar!",
      jaHaUmAnexoComEsseNome: "Já há um anexo com esse nome!",
      problema: "Problema",
      proposta: "Proposta",
      beneficios: "Benefícios",
      frequenciaDeUso: "Frequência de Uso",
      tamanho: "Tamanho",
      secaoDeTi: "Seção de TI",
      buSolicitante: "BU Solicitante",
      busBeneficiadas: "BUs Beneficiadas",
      forum: "Fórum",
      anexos: "Anexos",
      nenhumAnexoAdicionado: "Nenhum anexo adicionado",
      digiteTituloDaDemanda: "Digite o título da demanda...",
      digiteProblema: "Digite o problema...",
      digiteProposta: "Digite a proposta...",
      digiteFrequenciaDeUso: "Digite a frequência de uso...",
      baixar: "Baixar",
      remover: "Remover",
      botaoRecusar: "Recusar",
      botaoDevolver: "Devolver",
      botaoAceitar: "Aceitar",
      botaoSalvar: "Salvar",
    },
    escopo: {
      titleExcluir: "Excluir",
    },
    formularioAnexosDemanda: {
      arrasteSolteParaAdicionarUmArquivo:
        "Arraste & Solte para Adicionar um Arquivo",
      solteParaAdicionarArquivo: "Solte para Adicionar um Arquivo",
      ou: "OU",
      pesquisarArquivos: "Pesquisar Arquivos",
      nome: "Nome",
      tipo: "Tipo",
      remover: "Remover",
    },
    formularioBeneficiosDemanda: {
      adicionar: "Adicionar",
    },
    formularioCustosProposta: {
      total: "Total",
      horas: "h",
      moeda: "R$",
      adicionarCustos: "Adicionar Custos",
    },
    formularioDadosDemanda: {
      titulo: "Título:",
      digiteTitulo: "Digite o título...",
      problema: "Problema:",
      digiteProblema: "Digite o problema...",
      proposta: "Proposta:",
      digiteProposta: "Digite a proposta...",
      frequenciaDeUso: "Frequência de uso:",
      digiteFrequenciaDeUso: "Digite a frequência de uso...",
    },
    formularioGeralProposta: {
      periodoDeExecucao: "Período de execução",
      digiteCodigo: "Digite o código...",
      a: "à",
      paybackSimples: "Payback simples",
      quantidade: "Qtd",
      dias: "Dias",
      semanas: "Semanas",
      meses: "Meses",
      codigoPpm: "Código PPM",
      linkDoJira: "Link do Jira",
      insiraLinkDoJira: "Insira o link do Jira...",
      responsavelResponsaveis: "Responsável/Responsáveis",
      titleAdicionarNovoResponsavel: "Adicionar um novo responsável",
      anexos: "Anexos",
      adicionarNovoAnexo: "Adicionar um novo anexo",
      nenhumAnexoAdicionado: "Nenhum anexo adicionado",
    },
    formularioPropostaProposta: {
      feedbacks: {
        feedback1: "Já há um anexo com esse nome!",
      },
      digiteTituloDaDemanda: "Digite o título da demanda...",
      problema: "Problema",
      digiteProblema: "Digite o problema...",
      proposta: "Proposta",
      digiteProposta: "Digite a proposta...",
      beneficios: "Beneficios",
      frequenciaDeUso: "Frequência de uso",
      digiteFrequencia: "Digite a frequência...",
      labelTamanho: "Tamanho",
      muitoPequeno: "Muito Pequeno",
      pequeno: "Pequeno",
      medio: "Médio",
      grande: "Grande",
      muitoGrande: "Muito Grande",
      nenhumaSecaoEncontrada: "Nenhuma seção encontrada",
      labelSecaoTi: "Seção TI",
      nenhumaBuEncontrada: "Nenhuma BU encontrada",
      buSolicitante: "BU Solicitante",
      labelBusBeneficiadas: "BUs Beneficiadas",
      selecioneUmaOuMaisBus: "Selecione uma ou mais BUs",
      nenhumForumEncontrado: "Nenhum fórum encontrado",
      labelForum: "Fórum",
      anexos: "Anexos",
      titleBaixar: "Baixar",
      titleRemover: "Remover",
      nenhumAnexoAdicionado: "Nenhum anexo adicionado",
    },
    Header: {
      paginaInicial: "Página Inicial",
    },
    idiomaModal: {
      idiomas: "Idiomas",
    },
    linhaTabelaCCs: {
      titleExcluirLinha: "Excluir linha",
      digiteCodigo: "Digite o código...",
    },
    linhaTabelaCustos: {
      digiteTipo: "Digite o tipo...",
      digitePerfil: "Digite o perfil...",
      digitePeriodo: "Digite o período...",
      digiteHoras: "Digite as horas...",
      digiteValor: "Digite o valor...",
      titleExcluirLinha: "Excluir linha",
    },
    modalAceitarDemanda: {
      informacoes: "Informações",
      tamanho: "Tamanho",
      muitoPequeno: "Muito Pequeno",
      pequeno: "Pequeno",
      medio: "Médio",
      grande: "Grande",
      muitoGrande: "Muito Grande",
      buSolicitante: "BU Solicitante",
      nenhumaBuEncontrada: "Nenhuma BU encontrada",
      busBeneficiadas: "BUs Beneficiadas",
      selecioneUmaOuMaisBus: "Selecione uma ou mais BUs",
      nenhumaSecaoEncontrada: "Nenhuma seção encontrada",
      secaoTi: "Seção TI",
      forum: "Fórum",
      nenhumForumEncontrado: "Nenhum fórum encontrado",
      anexos: "Anexos",
      nenhumAnexoAdicionado: "Nenhum anexo adicionado",
      cancelar: "Cancelar",
      aceitar: "Aceitar",
    },
    modalConfirmacao: {
      mensagensModal: {
        descartarRascunho: "Deseja descartar o escopo?",
        sairCriacao: "Deseja sair da criação da demanda?",
        enviarDemanda: "Deseja criar a demanda?",
        sairSemSalvar: "Deseja sair sem salvar?",
        descartarProposta: "Deseja descartar a proposta?",
        cancelarEdicao: "Deseja descartar as alterações?",
        aceitarDemanda: "Deseja aceitar a demanda?",
        confirmarExclusao: "Tem certeza que deseja excluir?",
        fecharChat: "Deseja realmente fechar este chat?",
      },
      mensagensBotao: {
        sim: "Sim",
        enviar: "Enviar",
        criar: "Criar",
        aceitar: "Aceitar",
      },
      cancelar: "Cancelar",
    },
    modalCriarAta: {
      feedback: "Preencha todos os campos obrigatórios!",
      criacaoDaAta: "Criação da Ata",
      digiteNumeroSequencial: "Digite o número sequencial...",
      dataDaReuniao: "Data da reunião:",
      botaoCancelar: "Cancelar",
      botaoCriar: "Criar",
    },
    modalFiltro: {
      status: "Status",
      labels: {
        aprovada: "Aprovada",
        reprovada: "Reprovada",
        aguardandoEdicao: "Aguardando Edição",
        aguardandoRevisao: "Aguardando Revisão",
        emAprovacao: "Em Aprovação",
        emAndamento: "Em Andamento",
      },
    },
    modalFiltroGerencia: {
      filtros: "Filtros",
      semResultados: "Sem Resultados",
      labelSolicitante: "Solicitante",
      forum: "Fórum",
      selecionar: "Selecionar",
      tamanho: "Tamanho",
      selecionar: "Selecionar",
      muitoPequeno: "Muito Pequeno",
      semResultados: "Sem Resultados",
      analistaResponsavel: "Analista Responsável",
      gerenteResponsavel: "Gerente Responsável",
      pequeno: "Pequeno",
      medio: "Médio",
      grande: "Grande",
      muitoGrande: "Muito Grande",
      departamento: "Departamento",
      numero: "Número",
      limparFilros: "Limpar Filtros",
    },
    modalHistoricoDemanda: {
      historico: "Histórico",
    },
    modalInformarMotivo: {
      informarMotivo: "Informar Motivo",
      informeMotivo: "Informe o motivo...",
      confirmar: "Confirmar",
    },
    modalMotivoRecusa: {
      motivoDaRecusa: "Motivo da recusa",
    },
    modalOrdenacao: {
      score: "Score",
      maiorScore: "Maior Score",
      menorScore: "Menor Score",
      titulo: "Título",
      az: "A-Z",
      za: "Z-A",
      data: "Data",
      maisRecente: "Mais recente",
      maisAntiga: "Mais antiga",
    },
    modalRecusarDemanda: {
      motivoDaRecusa: "Motivo da recusa",
      informeMotivo: "Informe o motivo...",
      enviar: "Enviar",
    },
    notificacaoComponente: {
      demandaDeNumero: "A demanda de número",
      foi: "foi",
      aprovada: "aprovada",
      reprovada: "reprovada",
      reprovadaPorFaltaDeInformacoes: "reprovada por falta de informações",
      diasAtras: "dias atrás",
      hoje: "hoje",
      umaSemanaAtras: "1 semana atrás",
      duasSemanasAtras: "2 semanas atrás",
      tresSemanasAtras: "3 semanas atrás",
      quatroSemanasAtras: "4 semanas atrás",
      maisDeUmMesAtras: "mais de 1 mês atrás",
    },
    notificacaoModal: {
      notificacaoLidaComSucesso: "Notificação lida com sucesso!",
      notificacoes: "Notificações",
      nenhumaNotificaçaoEncontrada: "Nenhuma notificação encontrada!",
      verTudo: "Ver tudo",
      verNotificacoes: "Ver notificações",
    },
    paginacao: {
      itensPorPagina: "Itens por página",
    },
    pauta: {
      deletar: "Deletar",
      comissao: "Comissão",
      analistaResponsavel: "Analista Responsável",
    },
    pautaAtaModoVisualizacao: {
      as: "às",
      numeroSequencial: "Num. Seq.",
      comissao: "Comissão",
      analistaResponsavel: "Anal. Resp.",
      deletar: "Deletar",
      data: "Data",
      nadaEncontrado: "Nada encontrado",
      tenteNovamenteMaisTarde: "Tente novamente mais tarde",
    },
    propostaDeAta: {
      codigoPpm: "Código PPM",
      tituloDaProposta: "Título da Proposta",
      responsavelDoNegocio: "Responsável do Negócio",
      problema: "Problema",
      proposta: "Proposta",
      beneficios: "Benefícios",
      frequenciaDeUso: "Frequência de Uso",
      custos: "Custos",
      custosAqui: "Custos aqui",
      periodoDeExecucao: "Período de Execução",
      paybackSimples: "Payback Simples",
      linkDoJira: "Link Do Jira",
      anexos: "Anexos",
      parecerComissao: "Parecer Comissão",
      maisInformacoes: "Mais Informações",
      businessCase: "Business Case",
      aprovado: "Aprovado",
      escrevaNovaInformacao: "Escreva a nova informação...",
      parecerDG: "Parecer DG",
      aprovado: "Aprovado",
      reprovado: "Reprovado",
    },
    responsavelNegocio: {
      responsavelDoNegocio: "Responsável do negócio",
      insiraResponsavelDoNegocio: "Insira o responsável pelo negócio...",
      area: "Área",
      insiraAreaDoResponsavel: "Insira a área do responsável...",
    },
    textEditor: {
      digiteAquiEscopoDaProposta: "Digite aqui o escopo da proposta...",
    },
    userModal: {
      normal: "Normal",
      pequeno: "Pequeno",
      muitoPequeno: "Muito Pequeno",
      grande: "Grande",
      muitoGrande: "Muito Grande",
      configuracoes: "Configurações",
      escopos: "Escopos",
      chats: "Chats",
      A: "A",
      diminuirFonte: "Diminuir fonte",
      aumentarFonte: "Aumentar fonte",
      modoClaroEscuro: "Modo Claro/Escuro",
      sair: "Sair",
    },
    chat: {
      tour: {
        tour1:
          "Neste input você pode pesquisar pelos chats por nome do usuário e pelo número sequêncial da demanda.",
        tour2:
          "Aqui fica os chats abertos, tendo o nome do usuário, o número sequêncial da demanda e de qual demanda pertence.",
        tour3:
          "Neste botão pode escolher entre minimizar o chat ou encerrar a conversa (fechando o chat).",
        tour4:
          "Aqui pode escrever o que deseja enviar, podendo também anexar algum arquivo.",
      },
      pesquisarPorNome: "Pesquisar por usuário...",
      usuarioTour: {
        tour: "Tour",
        gerente: "Gerente",
        mostrarNoTour: "Mostrar no tour",
        olaTudoBem: "Olá, tudo bem?",
        tudoSimVoce:
          "Tudo sim, e você? Estou aqui para te mostrar como funciona o chat.",
        eu: "Eu",
        bemTambemObrigado: "Bem também, obrigado.",
      },
      usuario: "Usuário",
      selecioneAlgumaConversa: "Selecione alguma conversa",
      miniChatAberto: "Mini chat aberto",
      opcoes: "Opções",
      abrirPopUp: "Abrir pop-up",
      encerrarChat: "Encerrar chat",
      reabrirChat: "Reabrir chat",
      escrevaSuaMensagem: "Escreva sua mensagem...",
      enviarAnexo: "Enviar anexo",
      enviarMensagem: "Enviar mensagem",
      analista: "Analista",
      solicitante: "Solicitante",
      cargo: "Cargo",
      chatEncerrado: "Chat encerrado com sucesso!",
      chatReaberto: "Chat reaberto com sucesso!",
      chatEncerradoErro: "Não é possível entrar em um chat encerrado!",
      inputChatEncerrado:
        "Não é possível enviar mensagens, pois o chat foi encerrado.",
      anexoMuitoPesado: "O anexo é muito pesado!",
    },
    detalhesAta: {
      ataCriadaComSucesso: "Ata criada com sucesso!",
      ata: "Ata",
      numeroSequencial: "Número Sequencial",
      dataReuniao: "Data da Reunião",
      horaReuniao: "Hora da Reunião",
      data: "Data",
      inicio: "Início",
      fim: "Fim",
      sumario: "Sumário",
      proposta: "Proposta",
      voltar: "Voltar",
      navegacao: "Navegação",
      publicarAta: "Publicar Ata",
      proximo: "Próximo",
      publicar: "Publicar",
    },
    detalhesDemandaPagina: {
      tour: {
        tour1:
          "Essa é a página de detalhes da demanda. Aqui você pode ver todos os detalhes da demanda selecionada.",
        tour2: "Cicando aqui, você consegue baixar em PDF essa demanda.",
        tour3:
          "Clicando neste lápis, você consegue estar editando as informações.",
      },
    },
    detalhesPauta: {
      feedbacks: {
        feedback1: "Proposta removida da pauta com sucesso!",
      },
      baixarPDF: "Baixar PDF",
      pauta: "Pauta",
      numeroSequencial: "Número Sequencial",
      ano: "Ano",
      sumario: "Sumário",
      proposta: "Proposta",
      voltar: "Voltar",
      proximo: "Próximo",
      navegacao: "Navegação",
      criarAta: "Criar Ata",
    },
    detalhesPropostaPagina: {
      adicionarAPauta: "Adicionar à Pauta",
    },
    escopos: {
      tour: {
        tour1:
          "Aqui fica a barra de pesquisa, onde você pode pesquisar por um título.",
        tour2:
          "Aqui fica os escopos criados automaticamente, em cada escopo é possível abrir novamente para edição. É criado um escopo para cada projeto que você abre e não finaliza.",
        tour3:
          "Nesta área você consegue visualizar qual a porcentagem preenchida do formulário.",
        tour4: "Clicando na lixeira você exclui o escopo.",
      },
      pesquisarPorTitulo: "Pesquisar por título...",
      pesquisar: "Pesquisar",
      escopoDeletadoComSucesso: "Escopo deletado com sucesso!",
    },
    home: {
      tourAjuda: {
        barraDePesquisa:
          "Aqui fica a barra de pesquisa, onde você pode pesquisar por um título.",
        iconeOredenar:
          "Neste ícone você pode ordenar as suas demandas por título (A-Z ou Z-A), Score (Maior ao menor ou Menor ao maior) e pela data (Mais nova à mais velha ou Mais velha à Mais nova).",
        botaoFiltrar:
          "Neste botão você pode filtrar suas demandas por seus status.",
        modoVisualizacao:
          "Nesta parte você pode trocar o modo de visualização das suas demandas. Você pode escolher entre visualizar as demandas em forma de lista ou em forma de cards.",
        criarNovaDemanda:
          "Aqui você consegue iniciar a criação de uma nova demanda.",
        areaDemanda:
          "Nesta área você consegue observar suas demandas. Você pode clicar em uma demanda para ver mais detalhes.",
        statusDemanda:
          "Nesta área você consegue visualizar o status atual da demanda.",
        botaoMotivo:
          "Aqui consegue observar o motivo pelo qual foi recusado ou o motivo da edição.",
      },
      demandaCriadaComSucesso: "Demanda criada com sucesso!",
      minhasDemandas: "Minhas Demandas",
      meuDepartamento: "Meu Departamento",
      visualizacaoEmTabela: "Visualização em Tabela",
      visualizacaoEmBloco: "Visualização em Bloco",
      pesquisarPorTitulo: "Pesquisar por título...",
      pesquisar: "Pesquisar",
      ordenacao: "Ordenação",
      filtrar: "Filtrar",
      criarDemanda: "Criar Demanda",
      demandaTour: "Demanda Tour",
      esseUmExemploDeDemanda: "Esse é um exemplo de demanda",
      nomeDoSolicitante: "Nome do Solicitante",
    },
    homeGerencia: {
      toursMinhasDemandas: {
        tour6:
          "Nesta parte fica as suas demandas, podendo clicar em uma demanda para ver mais detalhes",
      },
      toursDemandas: {
        tour1:
          "Aqui é a barra de pesquisa, você pode pesquisar por algum título de alguma demanda",
        tour2:
          "Esse é icone de ordenação, onde poderá ordenar os itens por ordem alfabética, por score ou por data de criação",
        tour3:
          "Aqui fica o filtro, podendo filtrar por: Solicitante, Gerente Responsável, Fórum, Departamento, Tamanho e número",
        tour4:
          "Aqui fica o lugar para exportar as demandas em formato de planilha",
        tour5: "Aqui pode iniciar o processo de criação de uma nova demanda",
        tour6:
          "Nesta parte fica as demandas, podendo clicar em uma demanda para ver mais detalhes",
        tour7:
          "Clicando aqui você pode ver o histórico de alterações da demanda",
        tour8: "Nesta área você pode visualizar o status da demanda",
        tour9:
          "Clicando aqui você pode alterar o modo de visualização para tabela ou cards",
      },
      toursCriarPropostas: {
        tour1:
          "Nesta parte fica as demandas, podendo clicar em uma demanda para ver mais detalhes e/ou criar uma proposta",
        tour2: "Nesta área você pode visualizar o status da demanda",
        tour3: "Clique aqui para ver o histórico de alterações da demanda",
      },
      toursPropostas: {
        tour1:
          "Nesta parte fica as propostas, podendo clicar em uma proposta para ver mais detalhes.",
        tour2:
          "Clicando aqui você pode abrir o chat com o solicitante da demanda",
        tour3:
          "Clicando aqui você pode ver o histórico de alterações da proposta",
      },
      toursPautas: {
        tour1:
          "Nesta parte fica as pautas, podendo clicar em uma pauta para ver mais detalhes.",
        tour2: "Clicando aqui você pode excluir a pauta",
      },
      toursAtas: {
        tour1:
          "Nesta parte fica as atas, podendo clicar em uma ata para ver mais detalhes",
      },
      feedback: {
        feedback1: "Ata publicada com sucesso!",
        feedback2: "Demanda aceita com sucesso!",
        feedback3: "Demanda recusada com sucesso!",
        feedback4: "Demanda devolvida com sucesso!",
        feedback5: "Proposta criada com sucesso!",
        feedback6: "Pauta deletada com sucesso!",
        feedback7: "Proposta atualizada com sucesso!",
        feedback8: "Ata criada com sucesso!",
        feedback9: "Propostas atualizadas com sucesso!",
        feedback10: "Demanda criada com sucesso!",
        feedback11: "Você não pode abrir um chat com você mesmo!",
        feedback12: "Erro ao reconhcer a voz!",
        feedback13: "Navegador não é compatível!",
      },
      demandas: "Demandas",
      criarPropostas: "Criar Propostas",
      propostas: "Propostas",
      pautas: "Pautas",
      atas: "Atas",
      visualizacaoEmTabela: "Visualização em tabela",
      visualizacaoEmBloco: "Visualização em bloco",
      pesquisarPorTitulo: "Pesquisar por título...",
      pesquisar: "Pesquisar",
      gravarAudio: "Gravar Audio",
      ordenacao: "Ordenação",
      filtrar: "Filtrar",
      exportar: "Exportar",
      criarDemanda: "Criar Demanda",
      demandaParaTour: "Demanda para Tour",
    },
    login: {
      email: "Email",
      senha: "Senha",
      esqueciSenha: "Esqueci a senha",
      entrar: "Entrar",
      feedback: {
        dadosInvalidos: "Dados inválidos!",
        preenchaTodosOsCampos: "Preencha todos os campos!",
      },
    },
    notFound: {
      paginaNaoEncontrada: "Página não encontrada!",
      desculpePaginaNaoEncontrada:
        "Desculpe, a página informada não foi encontrada!",
      porfavorVolteParaPaginaPrincipal:
        "Por favor, volte para a página principal.",
      voltar: "Voltar",
    },
    notificacao: {
      notificacaoMArcadasComoNaoLidasComSucesso:
        "Notificações marcadas como não lidas com sucesso!",
      notificacaoMArcadasComoLidasComSucesso:
        "Notificações marcadas como lidas com sucesso!",
      notificacaoExcluidasComSucesso: "Notificações excluídas com sucesso!",
      notificacoes: "Notificações",
      deletar: "Deletar",
      marcarComoLido: "Marcar como lido",
      tipo: "Tipo",
      titulo: "Titulo",
      data: "Data",
      marcarComoNaoLido: "Marcar como não lido",
      marcarCommoLido: "Marcar como lido",
      naoHaNotificacoes: "Não há notificações",
    },
    detalhesProposta: {
      ppm: "PPM",
      solicitante: "Solicitante",
      objetivo: "Objetivo",
      proposta: "Proposta",
      problema: "Problema",
      escopoDaProposta: "Escopo da Proposta",
      periodoDeExecucao: "Período de Execução",
      payback: "Payback",
      reponsaveisPeloNegocio: "Responsáveis pelo Negócio",
      ate: "até",
      tabelaDeCustos: "Tabela de Custos",
      tipoDaDespesa: "Tipo da Despesa",
      perfilDaDespesa: "Perfil da Despesa",
      periodoDeExecucaoTabela: "Período de Execução (meses)",
      horas: "Horas",
      valorHora: "Valor Hora",
      total: "Total",
      ccs: "CCs",
      porcentagem: "Porcentagem",
      anexos: "Anexos",
      download: "Baixar",
      semAnexos: "Proposta sem anexos",
      beneficios: "Benefícios",
      semBeneficios: "Proposta sem benefícios",
      tipoBeneficio: "Tipo",
      valorMensal: "Valor Mensal",
      moeda: "Moeda",
      memoriaCalculo: "Memória de Cálculo",
      busBeneficiadas: "BUs Beneficiadas",
      semBuBeneficiada: "Proposta sem BUs beneficiadas",
      buSolicitante: "BU Solicitante",
      frequencia: "Frequência",
      linkJira: "Link Jira",
      tamanho: "Tamanho",
      data: "DATA",
      gerente: "Gerente",
      pareceres: "Pareceres",
      forum: "Fórum",
      aprovado: "Aprovado",
      reprovado: "Reprovado",
      devolvido: "Devolvido",
      businessCase: "Business Case",
      parecer: "Parecer",
      maisInformacoes: "Mais Informações...",
      comissao: "Comissão",
      direcaoGeral: "Direção Geral",
      semInformacoesAdicionais: "Sem informações adicionais",
      semParecer: "Sem parecer",
    },
    modalAddPropostaPauta: {
      feedbacks: {
        feedback1: "Preencha todos os campos!",
        feedback2: "Pauta atualizada com sucesso!",
      },
      selecioneAPauta: "Selecione a pauta",
      propostas: "Propostas",
      comissao: "Comissão",
      adicionarComoProposta: "Adicionar como proposta",
      publicada: "Publicada",
      naoPublicada: "Não publicada",
      novaPauta: "Nova Pauta",
      adicionar: "Adicionar",
      numSequencial: "Nº Sequencial",
      essaPropostaJaSeEncontraEmUmaPauta:
        "Essa proposta já se encontra em uma pauta!",
    },
  },
  setTexts: () => {},
});

export default TextLanguageContext;
