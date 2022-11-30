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
        return (await axios.get(demanda + `/page?${page}`, { params: params, headers: { "Content-Type": "multipart/form-data" } })).data;
    }
}

export default new DemandaService();