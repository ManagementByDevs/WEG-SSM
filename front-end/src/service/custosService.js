import axios from "./api";

/** Classe para as funções de custos, ccs e tabelas de custos */
class CustosService {

    // Tabela de custos

    /** Função para criar uma nova tabela de custos */
    async postTabela(tabelaCusto) {
        return (await axios.post(`/tabela-custo`, tabelaCusto, { headers: { "Content-Type": "application/json" } })).data;
    }

    /** Função para excluir uma tabela de custos pelo seu ID */
    async deleteTabela(tabelaCustoId) {
        return (await axios.delete(`/tabela-custo/${tabelaCustoId}`)).data;
    }




    // Custos
    
    /** Função para criar um novo custo dentro de uma tabela */
    async postCusto(custo) {
        return (await axios.post(`/custo`, custo, { headers: { "Content-Type": "application/json" } })).data;
    }

    /** Função para excluir um custo pelo seu ID */
    async deleteCusto(custoId) {
        return (await axios.delete(`/custo/${custoId}`)).data;
    }




    // CCs

    /** Função para criar uma nova CC dentro da tabela de custos */
    async postCC(cc) {
        return (await axios.post(`/cc`, cc, { headers: { "Content-Type": "application/json" } })).data;
    }

    /** Função para excluir uma CC pelo seu ID */
    async deleteCC(ccId) {
        return (await axios.delete(`/cc/${ccId}`)).data;
    }
}

export default new CustosService();