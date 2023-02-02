import axios from "./api";

class ResponsavelNegocioService {
    
    async post(responsavelNegocio) {
        return (await axios.post(`/responsavel_negocio/`, responsavelNegocio, { headers: { "Content-Type": "application/json" } })).data;
    }
    
}

export default new ResponsavelNegocioService();