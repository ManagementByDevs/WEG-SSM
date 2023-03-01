import axios from "./api";

const excel = "/excel";

class ExportExcelService {

    async exportExcel() {
        return (await axios.get(`${excel}/demandas`, { responseType: 'arraybuffer', headers: { "Content-Type": "multipart/form-data" } })).data;
    }

}

export default new ExportExcelService();