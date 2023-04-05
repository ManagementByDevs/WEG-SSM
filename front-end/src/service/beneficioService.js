import axios from "./api";

/** Classe service para os benefícios */
class BeneficioService {

    /** Função para salvar um novo benefício */
    async post(beneficio) {
        return (await axios.post(`/beneficio/`, beneficio, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    /** Função para atualizar um benefício */
    async put(beneficio) {
        return (await axios.put(`/beneficio/`, beneficio, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    /** Função para excluir um benefício pelo seu ID */
    async delete(idBeneficio) {
        return (await axios.delete(`/beneficio/${idBeneficio}`, { withCredentials: true })).data;
    }
}

export default new BeneficioService();