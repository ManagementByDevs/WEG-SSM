import axios from "./api";

const pdf = "/pdf";

class ExportPDFService {

    async exportDemanda(demandaId) {
        return (await axios.get(`${pdf}/demanda/${demandaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportProposta(propostaId) {
        return (await axios.get(`${pdf}/proposta/${propostaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportPauta(pautaId) {
        return (await axios.get(`${pdf}/pauta/${pautaId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportAta(ataId) {
        return (await axios.get(`${pdf}/ata/${ataId}`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }
}

export default new ExportPDFService();