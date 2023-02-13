import axios from "./api";

class EscopoPropostaService {

    async buscarPorUsuario(usuarioId, ordenacao) {
        return (await axios.get(`/escopo-proposta/usuario/${usuarioId}/?${ordenacao}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async buscarPorTitulo(usuarioId, titulo, ordenacao) {
        return (await axios.get(`/escopo-proposta/titulo/${usuarioId}/${titulo}/?${ordenacao}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async post(escopoProposta) {
        return (await axios.post(`/escopo-proposta`, escopoProposta, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async salvarDados(escopoProposta) {
        return (await axios.put(`/escopo-proposta`, escopoProposta, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async excluirEscopo(idEscopoProposta) {
        return (await axios.delete(`/escopo-proposta/${idEscopoProposta}`)).data;
    }
}

export default new EscopoPropostaService();