import axios from "./api";

const demanda = "/demanda";

class DemandaService {
    async get() {
        return (await axios.get(demanda)).data;
    }

    async getById(id) {
        return (await axios.get(demanda + "/" + id)).data;
    }

    async getByPPM(ppm) {
        return (await axios.get(demanda + `/ppm/${ppm}`)).data;
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
            return (await axios.put(`/demanda/aceite`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        } else {
            return (await axios.put(`/demanda/manter-arquivos-velhos`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        }
    }

    async putSemAnexos(demanda) {
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));

        return (await axios.put(`/demanda/manter-arquivos-velhos`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async addHistorico(idDemanda, texto, documento, usuario) {
        let form = new FormData();
        form.set("acao", texto);
        form.set("documento", documento);
        form.set("usuarioId", usuario);

        return (await axios.put(`/demanda/add-historico/${idDemanda}`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }
}

export default new DemandaService();