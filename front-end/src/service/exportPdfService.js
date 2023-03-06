import axios from "./api";

const pdf = "/pdf";

class ExportPDFService {

    async exportEscopo(escopo) {
        return (await axios.get(`${pdf}/escopo`, escopo, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async exportDemanda(demandaId) {
        return (await axios.get(`${pdf}/demanda/${demandaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    async exportProposta(propostaId) {
        return (await axios.get(`${pdf}/proposta/${propostaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }
}

export default new ExportPDFService();