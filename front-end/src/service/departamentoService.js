import axios from "./api";

class DepartamentoService {
    
    async getAll() {
        return (await axios.get(`/departamento`)).data;
    }
}

export default new DepartamentoService();