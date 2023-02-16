import axios from "./api";

const proposta = "/proposta";

class PropostaService {
    async get() {
        return (await axios.get(proposta)).data;
    }

    async getById(id) {
        return (await axios.get(proposta + "/" + id)).data;
    }

    async getByPPM(ppm) {
        return (await axios.get(proposta + `/ppm/${ppm}`)).data;
    }

    async getPage(params, page) {
        if (params.gerente != null) {
            params.gerenteJson = JSON.stringify(params.gerente);
            delete params.gerente;
        }
        if (params.solicitante != null) {
            params.solicitanteJson = JSON.stringify(params.solicitante);
            delete params.solicitante;
        }
        if (params.analista != null) {
            params.analistaJson = JSON.stringify(params.analista);
            delete params.analista;
        }

        return (await axios.get(proposta + `/page?${page}`, { params: params, headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async post(proposta, arquivos) {
        let form = new FormData();

        form.append("proposta", JSON.stringify(proposta));
        for (let arquivo of arquivos) {
            form.append("anexos", arquivo);
        }

        if (arquivos.length > 0) {
            return (await axios.post(`/proposta/`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        } else {
            return (await axios.post(`/proposta/sem-arquivos/`, form, { headers: { "Content-Type": "multipart/form-data", 'Access-Control-Allow-Origin': '*' } })).data;
        }
    }

    async atualizarStatus(idProposta, statusNovo) {
        return (await axios.put(`/proposta/status/${idProposta}/${statusNovo}`, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async put(proposta, arquivos) {
        let form = new FormData();

        form.append("proposta", JSON.stringify(proposta));
        for (let arquivo of arquivos) {
            form.append("anexos", arquivo);
        }

        if (arquivos.length > 0) {
            return (await axios.put(`/proposta`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        } else {
            return (await axios.put(`/proposta/sem-arquivos`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
        }
    }
}

export default new PropostaService();