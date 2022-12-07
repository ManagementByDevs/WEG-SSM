import axios from "./api";

class EscopoService {

    async buscarPorUsuario(usuarioId) {
        return (await axios.get(`/escopo/usuario/${usuarioId}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async postNew(usuarioId) {
        return (await axios.post(`/escopo/novo/${usuarioId}`, { headers: { "Content-Type": "application/json" } })).data;
    }

    async salvarDados(escopo) {
        return (await axios.put(`/escopo/dados`, escopo, { headers: { "Content-Type": "application/json" } })).data;
    }

    async salvarAnexosEscopo(idEscopo, anexos) {
        let form = new FormData();
        for (let anexo of anexos) {
            form.append("anexos", anexo);
        }

        return (await axios.put(`/escopo/anexos/${idEscopo}`, form, { headers: { "Content-Type": "multipart/form-data" } }))
    }

    async excluirEscopo(idEscopo) {
        return (await axios.delete(`/escopo/${idEscopo}`)).data;
    }

    async removerAnexos(idEscopo) {
        return (await axios.put(`/escopo/remover-anexos/${idEscopo}`, { headers: { "Content-Type": "application/json" } })).data;
    }
}

export default new EscopoService();