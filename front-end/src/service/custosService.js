import axios from "./api";

class CustosService {

    async postTabela(tabelaCusto) {
        return (await axios.post(`tabela-custo`, tabelaCusto, { headers: { "Content-Type": "application/json" } })).data;
    }

}

export default new CustosService();