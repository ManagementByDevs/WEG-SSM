import axios from "./api";

const excel = "/excel";

class ExportExcelService {
    async exportExcel(listaDemandas) {

        let form = new FormData();

        for(let demanda of listaDemandas) {
            form.append("demandas", demanda);
        }

        return (await axios.post(`${excel}/demandas`, form,  { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }

}

export default new ExportExcelService();