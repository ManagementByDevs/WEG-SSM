import axios from "./api";

const pdf = "/pdf";

class ExportPDFService {

    async exportDemanda(demandaId) {
        return (await axios.get(`${pdf}/demanda/${demandaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async exportProposta(propostaId) {
        return (await axios.get(`${pdf}/proposta/${propostaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }
}

export default new ExportPDFService();