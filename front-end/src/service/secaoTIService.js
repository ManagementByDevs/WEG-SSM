import axios from "./api";

class SecaoTIService {
    
    async getAll() {
        return (await axios.get(`/secao_ti`)).data;
    }
}

export default new SecaoTIService();