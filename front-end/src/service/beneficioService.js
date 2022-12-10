import axios from "./api";

class BeneficioService {

    async post(beneficio) {
        return (await axios.post(`/beneficio/`, beneficio, { headers: { "Content-Type": "application/json" } })).data;
    }

    async put(beneficio) {
        return (await axios.post(`/beneficio/`, beneficio, { headers: { "Content-Type": "application/json" } })).data;
    }

    async delete(idBeneficio) {
        return (await axios.delete(`/beneficio/${idBeneficio}`)).data;
    }
}

export default new BeneficioService();