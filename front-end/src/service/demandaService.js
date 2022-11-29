import axios from "./api";

const demanda = "/demanda";

class DemandaService {

    async getPage(params, page) {
        return (await axios.get(demanda + `/page?${page}`, { params: params })).data;
    }
}

export default new DemandaService();