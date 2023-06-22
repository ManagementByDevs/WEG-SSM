import axios from "./api";

/** Classe service para os benefícios */
class BeneficioService {

    /** Função para salvar um novo benefício */
    async post() {
        return (await axios.post(`/beneficio`, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    /** Função para atualizar um benefício */
    async put(beneficio) {
        let newBeneficio = { ...beneficio }
        delete newBeneficio.id;
        newBeneficio.tipoBeneficio = newBeneficio.tipoBeneficio.toUpperCase();

        return (await axios.put(`/beneficio/${beneficio.id}`, newBeneficio, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    /** Função para excluir um benefício pelo seu ID */
    async delete(idBeneficio) {
        return (await axios.delete(`/beneficio/${idBeneficio}`, { withCredentials: true })).data;
    }
}

export default new BeneficioService();