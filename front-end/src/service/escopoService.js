import axios from "./api";

class EscopoService {

    async buscarPagina(params, page) {

        let newParams = {...params};
        if(params.usuario) {
            newParams.usuario = JSON.stringify(params.usuario);
        }

        return (await axios.get(`/escopo/page?${page}`, { params: newParams, headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async buscarPorUsuario(usuarioId, ordenacao) {
        return (await axios.get(`/escopo/usuario/${usuarioId}/?${ordenacao}`, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    async buscarPorTitulo(usuarioId, titulo, ordenacao) {
        return (await axios.get(`/escopo/titulo/${usuarioId}/${titulo}/?${ordenacao}`, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    async buscarPorId(idEscopo) {
        return (await axios.get(`/escopo/id/${idEscopo}`, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    async postNew(usuarioId) {
        return (await axios.post(`/escopo/novo/${usuarioId}`, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    async salvarDados(escopo) {
        try {
            const data = await axios.put(`/escopo`, escopo, { headers: { "Content-Type": "application/json" }, withCredentials: true });
            return data.data;
        } catch (error) {
            throw error;
        }
    }

    async excluirEscopo(idEscopo) {
        return (await axios.delete(`/escopo/${idEscopo}`)).data;
    }
}

export default new EscopoService();