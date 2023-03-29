import axios from "./api";

class EscopoPropostaService {

    async buscarPorUsuario(usuarioId, ordenacao) {
        return (await axios.get(`/escopo-proposta/usuario/${usuarioId}/?${ordenacao}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async buscarPorTitulo(usuarioId, titulo, ordenacao) {
        return (await axios.get(`/escopo-proposta/titulo/${usuarioId}/${titulo}/?${ordenacao}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async buscarPorDemanda(demandaId) {
        return (await axios.get(`/escopo-proposta/demanda/${demandaId}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async post(escopoProposta) {
        let form = new FormData();
        form.append("escopo-proposta", JSON.stringify(escopoProposta));

        return (await axios.post(`/escopo-proposta`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async salvarDados(escopoProposta, listaIdsAnexos) {
        let form = new FormData();
        form.append("escopo-proposta", JSON.stringify(escopoProposta));
        for(const id of listaIdsAnexos) {
            form.append("idsAnexos", id);
        }

        return (await axios.put(`/escopo-proposta`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async excluirEscopo(idEscopoProposta) {
        return (await axios.delete(`/escopo-proposta/${idEscopoProposta}`)).data;
    }
}

export default new EscopoPropostaService();