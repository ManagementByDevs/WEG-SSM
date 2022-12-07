import axios from "./api";

class EscopoService {

    async postNew(usuarioId) {
        return (await axios.post(`/escopo/novo/${usuarioId}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async salvarDados(escopo) {
        return (await axios.put(`/escopo/dados`, escopo, { headers: { "Content-Type": "application/json" } })).data;
    }
}

export default new EscopoService();