import axios from "./api";

const demanda = "/demanda";

class DemandaService {
    async get() {
        return (await axios.get(demanda, { withCredentials: true })).data;
    }

    async getById(id, params) {

        let newParams = {};
        if (params?.usuario != null) {
            newParams.usuario = JSON.stringify(params.usuario);
        }
        if (params?.status != null) {
            newParams.status = params.status;
        }
        return (await axios.get(demanda + "/" + id, { params: newParams, withCredentials: true })).data;
    }

    async getByPPM(ppm) {
        return (await axios.get(demanda + `/ppm/${ppm}`, { withCredentials: true })).data;
    }

    async getPage(params, page) {

        let newParams = {};
        if(params.id != null) {
            newParams.id = params.id;
        }
        if(params.codigoPPM != null) {
            newParams.codigoPPM = params.codigoPPM;
        }
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
        if (params.status != null) {
            newParams.status = params.status;
        }
        if(params.tamanho != "") {
            newParams.tamanho = params.tamanho;
        }
        return (await axios.get(demanda + `/page?${page}`, { params: newParams, headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async post(demanda) {
        let form = new FormData();
        form.append("demanda", JSON.stringify(demanda));

        return (await axios.post(`/demanda`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async atualizarStatus(idDemanda, statusNovo) {
        return (await axios.put(`/demanda/status/${idDemanda}/${statusNovo}`, { withCredentials: true })).data;
    }

    async put(demanda) {
        let form = new FormData();
        form.append("demanda", JSON.stringify(demanda));

        return (await axios.put(`/demanda`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async addHistorico(idDemanda, historico, arquivo) {
        let form = new FormData();
        form.set("historico", JSON.stringify(historico));
        form.append("documento", arquivo);

        return (await axios.put(`/demanda/add-historico/${idDemanda}`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }
}

export default new DemandaService();