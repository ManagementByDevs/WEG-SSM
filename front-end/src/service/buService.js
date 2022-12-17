import axios from "./api";

class BuService {
    
    async getAll() {
        return (await axios.get(`/bu`)).data;
    }
}

export default new BuService();