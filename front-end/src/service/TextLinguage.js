const TextLinguage = (linguagem) => {
    linguagem == "pt" ? {
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
        
    } : linguagem == "en" ? {

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

    } : linguagem == "ch" ? {
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
    } : null;
}

export default TextLinguage;