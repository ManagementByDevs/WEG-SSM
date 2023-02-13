import axios from "./api";

class CustosService {

    // Tabela de custos
    async postTabela(tabelaCusto) {
        return (await axios.post(`/tabela-custo`, tabelaCusto, { headers: { "Content-Type": "application/json" } })).data;
    }

    async deleteTabela(tabelaCustoId) {
        return (await axios.delete(`/tabela-custo/${tabelaCustoId}`)).data;
    }


    // Custos
    async postCusto(custo) {
        return (await axios.post(`/custo`, custo, { headers: { "Content-Type": "application/json" } })).data;
    }

    async deleteCusto(custoId) {
        return (await axios.delete(`/custo/${custoId}`)).data;
    }


    // CCs
    async postCC(cc) {
        return (await axios.post(`/cc`, cc, { headers: { "Content-Type": "application/json" } })).data;
    }

    async deleteCC(ccId) {
        return (await axios.delete(`/cc/${ccId}`)).data;
    }
}

export default new CustosService();