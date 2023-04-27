import axios from "./api";

const excel = "/excel";

class ExportExcelService {
    async exportDemandasBacklogToExcel(listaDemandas) {

        let form = new FormData();

        for (let demanda of listaDemandas) {
            form.append("demandas_backlog", demanda);
        }

        return (await axios.post(`${excel}/demandas_backlog`, form, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportDemandasAssessmentToExcel(listaDemandas) {

        let form = new FormData();

        for(let demanda of listaDemandas) {
            form.append("demandas_assessment", demanda);
        }

        return (await axios.post(`${excel}/demandas_assessment`, form,  { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportPropostasToExcel(listaPropostas) {

        let form = new FormData();

        for(let proposta of listaPropostas) {
            form.append("propostas", proposta);
        }

        return (await axios.post(`${excel}/propostas`, form,  { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportPautasToExcel(listaPautas) {

        let form = new FormData();

        for(let pauta of listaPautas) {
            form.append("pautas", pauta);
        }

        return (await axios.post(`${excel}/pautas`, form,  { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    async exportAtasToExcel(listaAtas) {

        let form = new FormData();

        for(let ata of listaAtas) {
            form.append("atas", ata);
        }

        return (await axios.post(`${excel}/atas`, form,  { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

}

export default new ExportExcelService();