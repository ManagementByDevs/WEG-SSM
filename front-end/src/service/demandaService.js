import axios from "./api";

const demanda = "/demanda";

class DemandaService {
    async get() {
        return (await axios.get(demanda)).data;
    }

    async getPage(params, page) {

        if (params.departamento != null) {
            params.departamento = JSON.stringify(params.departamento);
        }
        if (params.forum != null) {
            params.forum = JSON.stringify(params.forum);
        }
        if (params.gerente != null) {
            params.gerente = JSON.stringify(params.gerente);
        }
        if (params.solicitante != null) {
            params.solicitante = JSON.stringify(params.solicitante);
        }
        if (params.analista != null) {
            params.analista = JSON.stringify(params.analista);
        }
        return (await axios.get(demanda + `/page?${page}`, { params: params, headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async post(demanda, arquivos, usuarioId) {
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));
        for (let arquivo of arquivos) {
            form.append("anexos", arquivo);
        }

        if (arquivos.length > 0) {
            return (await axios.post(`/demanda/${usuarioId}`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        } else {
            return (await axios.post(`/demanda/sem-arquivos/${usuarioId}`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        }
    }

    async atualizarStatus(idDemanda, statusNovo) {
        return (await axios.put(`/demanda/status/${idDemanda}/${statusNovo}`, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async put(demanda, arquivos) {
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));
        for (let arquivo of arquivos) {
            form.append("anexos", arquivo);
        }

        if (arquivos.length > 0) {
            return (await axios.put(`/demanda`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        } else {
            return (await axios.put(`/demanda/sem-arquivos`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        }
    }

    async putSemAnexos(demanda) {
        console.log("aaaa")
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));

        return (await axios.put(`/demanda/manter-arquivos-velhos`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }
}

export default new DemandaService();