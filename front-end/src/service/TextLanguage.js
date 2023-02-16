const TextLanguage = (linguagem) => {
    return linguagem == "pt" ? {
        barraProgressaoDemanda: {
            erroPularPasso: "Você não pode pular um passo que não é opcional!",
            botaoVoltar: "Voltar",
            botaoPular: "Pular",
            botaoCriar: "Criar",
            botaoProximo: "Próximo",
            mensagemFeedback: "Preencha todos os campos obrigatórios!"
        },
        barraProgressaoProposta: {
            botaoVoltar: "Voltar",
            botaoCriar: "Criar",
            botaoProximo: "Próximo",
        },
        beneficios: {
            beneficios: "Benefícios",
            real: "Real",
            potencial: "Potencial",
            qualitativo: "Qualitativo",
            valorMensal: "Valor Mensal",
            moeda: "Moeda",
            tiposDeMoeda: {
                real: "BRL",
                dolar: "USD",
                euro: "EUR",
            },
            memoriaCalcula: "Memória de cálculo",
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
            digiteValorPensal: "Digite o valor mensal...",
            tiposDeMoeda: {
                real: "BRL",
                dolar: "USD",
                euro: "EUR",
            },
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
            moeda: "R$",
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
            gerenteResponsavel: "Gerente Responsável",
        },
        demandaGerenciaModoVisualização: {
            backlog: "Backlog",
            assessment: "Assessment",
            titulo: "Título",
            solicitante: "Solicitante",
            departamento: "Departamento",
            gerenteResponsavel: "Gerente Responsável",
            status: "Status",
            data: "Data",
            naoAtribuido: "Não atribuído",
            historico: "Histórico",
            chat: "Chat",
            nadaEncontrado: "Nada encontrado",
            tenteNovamenteMaisTarde: "Tente novamente mais tarde",
        },
        demandaModoVisualizacao: {
            status: {
                reprovada: "Reprovada",
                aguardandoRevisao: "Aguardando Revisão",
                aguardandoEdicao: "Aguardando Edição",
                emAprovacao: "Em Aprovação",
                aprovada: "Aprovada",
            },
            codigo: "Código",
            titulo: "Titulo",
            status: "Status",
            data: "Data",
            motivo: "Motivo",
            nadaEncontrado: "Nada encontrado",
            tenteNovamenteMaisTarde: "Tente novamente mais tarde",
        },
        DetalhesDemanda: {
            facaAlgumaAlteracaoParaPoderSalvar: "Faça alguma alteração para poder salvar!",
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
            arrasteSolteParaAdicionarUmArquivo: "Arraste & Solte para Adicionar um Arquivo",
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
            moeda: "R$",
            adicionarCustos: "Adicionar Custos",
        },
        formularioDadosDemanda: {
            titulo: "Título",
            digiteTitulo: "Digite o título...",
            problema: "Problema",
            digiteProblema: "Digite o problema...",
            proposta: "Proposta",
            digiteProposta: "Digite a proposta...",
            frequenciaDeUso: "Frequencia de uso",
            digiteFrequenciaDeUso: "Digite a frequencia de uso...",
        },
        formularioGeralProposta: {
            periodoDeExecucao: "Período de execução",
            digiteCodigo: "Digite o código...",
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
            digiteTituloDaDemanda: "Digite o título da demanda...",
            problema: "Problema",
            digiteProblema: "Digite o problema...",
            proposta: "Proposta",
            digiteProposta: "Digite a proposta...",
            beneficios: "Beneficios",
            frequenciaDeUso: "Frequência de uso",
            digiteFrequencia: "Digite a frequência...",
            labelTamanho: "Tamanho",
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
        linhaTabelaCcs: {
            titleExcluirLinha: "Excluir linha",
            digiteCodigo: "Digite o código...",
        },
        linhaTabelaCustos: {
            tipo: "Tipo",
            tipo1: "Tipo 1",
            tipo2: "Tipo 2",
            tipo3: "Tipo 3",
            perfil: "Perfil",
            perfil1: "Perfil 1",
            perfil2: "Perfil 2",
            perfil3: "Perfil 3",
            digitePeriodo: "Digite o período...",
            digiteHoras: "Digite as horas...",
            digiteValor: "Digite o valor...",
            titleExcluirLinha: "Excluir linha",
            secaoTi1: "Seção TI 1",
            secaoTi2: "Seção TI 2",
            secaoTi3: "Seção TI 3",
            informacoes: "Informações",
            labelTamanho: "Tamanho",
            labelBuSolicitante: "BU Solicitante",
            nenhumaBuEncontrada: "Nenhuma BU encontrada",
            busBeneficiadas: "BUs Beneficiadas",
            selecioneUmaOuMaisBus: "Selecione Uma Ou Mais Bus",
            nenhumaSecaoEncontrada: "Nenhuma seção encontrada",
            labelSecaoTi: "Seção TI",
            label: "Fórum",
            nenhumForumEncontrado: "Nenhum fórum encontrado",
            anexos: "Anexos",
            nenhumAnexoAdicionado: "Nenhum anexo adicionado",
            cancelar: "Cancelar",
            aceitar: "Aceitar",
            selecionePauta: "Selecione a Pauta",
            propostas: "Propostas",
            comissao: "Comissão",
            exemplo1: "Exemplo 1",
            exemplo2: "Exemplo 2",
            propostas: "Propostas",
            adicionarComoProposta: "Adicionar Como Proposta",
            labelPublicada: "Publicada",
            labelNaoPublicada: "Não Publicada",
            butaoCriarPauta: "Criar Pauta",
            botaoAdicionar: "Adicionar",
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
            labelSolicitante: "Solicitante",
            forum: "Fórum",
            selecionar: "Selecionar",
            tamanho: "Tamanho",
            selecionar: "Selecionar",
            muitoPequeno: "Muito Pequeno",
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
        notificacao: {
            diasAtras: "dias atrás",
            hoje: "hoje",
            umaSemanaAtras: "1 semana atrás",
            duasSemanasAtras: "2 semanas atrás",
            tresSemanasAtras: "3 semanas atrás",
            quatroSemanasAtras: "4 semanas atrás",
            umMesAtras: "1 mês atrás",
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
            20: "20",
            40: "40",
            60: "60",
            80: "80",
        },
        pauta: {
            comissao: "Comissão",
            analistaResponsavel: "Analista Responsável",
        },
        pautaAtaModoVisualizacao: {
            numeroSequencial: "Num. Seq.",
            comissao: "Comissão",
            analistaResponsavel: "Anal. Resp.",
            data: "Data",
            nadaEncontrados: "Nada encontrado",
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
            aprovado: "Aprovado",
            reprovado: "Reprovado",
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
            modoClaroEscuro: "Modo Claro/Escuro",
            sair: "Sair",
        },











        home: {
            tourAjuda: {
                barraDePesquisa: "Aqui fica a barra de pesquisa, onde você pode pesquisar por um título.",
                iconeOredenar: "Neste ícone você pode ordenar as suas demandas por título (A-Z ou Z-A), Score (Maior ao menor ou Menor ao maior) e pela data (Mais nova à mais velha ou Mais velha à Mais nova).",
                botaoFiltrar: "Neste botão você pode filtrar suas demandas por seus status.",
                modoVisualizacao: "Nesta parte você pode trocar o modo de visualização das suas demandas. Você pode escolher entre visualizar as demandas em forma de lista ou em forma de cards.",
                criarNovaDemanda: "Aqui você consegue iniciar a criação de uma nova demanda.",
                areaDemanda: "Nesta área você consegue observar suas demandas. Você pode clicar em uma demanda para ver mais detalhes.",
                statusDemanda: "Nesta área você consegue visualizar o status atual da demanda.",
                botaoMotivo: "Aqui consegue observar o motivo pelo qual foi recusado ou o motivo da edição.",
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
        }

    } : linguagem == "en" ? {
        barraProgressaoDemanda: {
            erroPularPasso: "You can't skip a step that is not optional!",
            botaoVoltar: "Back",
            botaoPular: "Skip",
            botaoCriar: "Create",
            botaoProximo: "Next",
            mensagemFeedback: "Fill in all required fields!"
        },
        barraProgressaoProposta: {
            botaoVoltar: "Back",
            botaoCriar: "Create",
            botaoProximo: "Next",
        },
        beneficios: {
            beneficios: "Benefits",
            real: "Real",
            potencial: "Potential",
            qualitativo: "Qualitative",
            valorMensal: "Monthly Value",
            moeda: "Currency",
            tiposDeMoeda: {
                real: "BRL",
                dolar: "USD",
                euro: "EUR",
            },
            memoriaCalcula: "Calculation memory",
            digiteMemoriaCalculo: "Type the calculation memory...",
        },
















        home: {
            tourAjuda: {
                barraDePesquisa: "Here is the search bar, where you can search by a title.",
                iconeOredenar: "In this icon you can order your demands by title (A-Z or Z-A), Score (Highest to lowest or Lowest to highest) and by date (Newest to oldest or Oldest to Newest).",
                botaoFiltrar: "In this button you can filter your demands by their status.",
                modoVisualizacao: "In this part you can change the way you view your demands. You can choose between viewing the demands in list form or in card form.",
                criarNovaDemanda: "Here you can start creating a new demand.",
                areaDemanda: "In this area you can see your demands. You can click on a demand to see more details.",
                statusDemanda: "In this area you can see the current status of the demand.",
                botaoMotivo: "Here you can see the reason why it was rejected or the reason for the edit.",
            },
            demandaCriadaComSucesso: "Demand created successfully!",
            minhasDemandas: "My Demands",
            meuDepartamento: "My Department",
            visualizacaoEmTabela: "Table View",
            visualizacaoEmBloco: "Block View",
            pesquisarPorTitulo: "Search by title...",
            pesquisar: "Search",
            ordenacao: "Order",
            filtrar: "Filter",
            criarDemanda: "Create Demand",
            demandaTour: "Demand Tour",
            esseUmExemploDeDemanda: "This is an example of demand",
            nomeDoSolicitante: "Requester Name",
        }

    } : linguagem == "ch" ? {
        barraProgressaoDemanda: {
            erroPularPasso: "您不能跳過非可選步驟",
            botaoVoltar: "返回",
            botaoPular: "跳過",
            botaoCriar: "創建",
            botaoProximo: "下一步",
            mensagemFeedback: "填寫所有必填字段！"
        },
        barraProgressaoProposta: {
            botaoVoltar: "返回",
            botaoCriar: "創建",
            botaoProximo: "下一步",
        },
        beneficios: {
            beneficios: "好處",
            real: "真實",
            potencial: "潛在",
            qualitativo: "質量",
            valorMensal: "每月價值",
            moeda: "貨幣",
            tiposDeMoeda: {
                real: "BRL",
                dolar: "USD",
                euro: "EUR",
            },
            memoriaCalcula: "計算記憶體",
            digiteMemoriaCalculo: "輸入計算記憶體...",
        },



















        home: {
            tourAjuda: {
                barraDePesquisa: "这是搜索栏，您可以通过标题搜索。",
                iconeOredenar: "在这个图标中，您可以按标题（A-Z或Z-A）、分数（从高到低或从低到高）和日期（从新到旧或从旧到新）对您的需求进行排序。",
                botaoFiltrar: "在这个按钮中，您可以按需求状态过滤需求。",
                modoVisualizacao: "在这一部分中，您可以更改查看需求的方式。您可以选择以列表形式或卡片形式查看需求。",
                criarNovaDemanda: "在这里，您可以开始创建新需求。",
                areaDemanda: "在这个区域中，您可以查看需求。您可以单击需求以查看更多详细信息。",
                statusDemanda: "在这个区域中，您可以查看需求的当前状态。",
                botaoMotivo: "在这里，您可以查看拒绝的原因或编辑的原因。",
            },
            demandaCriadaComSucesso: "需求创建成功！",
            minhasDemandas: "我的需求",
            meuDepartamento: "我的部门",
            visualizacaoEmTabela: "表视图",
            visualizacaoEmBloco: "块视图",
            pesquisarPorTitulo: "按标题搜索...",
            pesquisar: "搜索",
            ordenacao: "排序",
            filtrar: "过滤",
            criarDemanda: "创建需求",
            demandaTour: "需求之旅",
            esseUmExemploDeDemanda: "这是一个需求示例",
            nomeDoSolicitante: "请求者姓名",
        }
    } : null;
}

export default TextLanguage;