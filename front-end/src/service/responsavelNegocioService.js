import axios from "./api";

class ResponsavelNegocioService {

    async post(responsavelNegocio) {
        return (await axios.post(`/responsavel_negocio`, responsavelNegocio, { headers: { "Content-Type": "application/json" } })).data;
    }

    async delete(responsavelNegocioId) {
        return (await axios.delete(`/responsavel_negocio/${responsavelNegocioId}`, { headers: { "Content-Type": "application/json" } })).data;
    }

}

export default new ResponsavelNegocioService();