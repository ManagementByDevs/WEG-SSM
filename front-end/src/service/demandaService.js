import axios from "./api";

const demanda = "/demanda";

class DemandaService {
    async get() {
        return (await axios.get(demanda, { withCredentials: true })).data;
    }

    async getById(id) {
        return (await axios.get(demanda + "/" + id, { withCredentials: true })).data;
    }

    async getByPPM(ppm) {
        return (await axios.get(demanda + `/ppm/${ppm}`, { withCredentials: true })).data;
    }

    async getPage(params, page) {

        let newParams = {};
        if (params.titulo != "") {
            newParams.titulo = params.titulo;
        }
        if (params.departamento != null) {
            newParams.departamento = JSON.stringify(params.departamento);
        }
        if (params.forum != null) {
            newParams.forum = JSON.stringify(params.forum);
        }
        if (params.gerente != null) {
            newParams.gerente = JSON.stringify(params.gerente);
        }
        if (params.solicitante != null) {
            newParams.solicitante = JSON.stringify(params.solicitante);
        }
        if (params.analista != null) {
            newParams.analista = JSON.stringify(params.analista);
        }
        if(params.status != null) {
            newParams.status = params.status;
        }
        return (await axios.get(demanda + `/page?${page}`, { params: newParams, headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async post(demanda, arquivos, usuarioId) {
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));
        for (let arquivo of arquivos) {
            form.append("anexos", arquivo);
        }

        if (arquivos.length > 0) {
            return (await axios.post(`/demanda/${usuarioId}`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
        } else {
            return (await axios.post(`/demanda/sem-arquivos/${usuarioId}`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
        }
    }

    async atualizarStatus(idDemanda, statusNovo) {
        return (await axios.put(`/demanda/status/${idDemanda}/${statusNovo}`, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async put(demanda, arquivos) {
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));
        for (let arquivo of arquivos) {
            form.append("anexos", arquivo);
        }

        if (arquivos.length > 0) {
            return (await axios.put(`/demanda/aceite`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
        } else {
            return (await axios.put(`/demanda/manter-arquivos-velhos`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
        }
    }

    async putSemAnexos(demanda) {
        let form = new FormData();

        form.append("demanda", JSON.stringify(demanda));

        return (await axios.put(`/demanda/manter-arquivos-velhos`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async addHistorico(idDemanda, texto, documento, usuario) {
        let form = new FormData();
        form.set("acao", texto);
        form.set("documento", documento);
        form.set("usuarioId", usuario);

        return (await axios.put(`/demanda/add-historico/${idDemanda}`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }
}

export default new DemandaService();