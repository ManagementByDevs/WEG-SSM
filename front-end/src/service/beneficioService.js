import axios from "./api";

/** Classe service para os benefícios */
class BeneficioService {

    /** Função para salvar um novo benefício */
    async post() {
        return (await axios.post(`/beneficio`, { headers: { "Content-Type": "application/json" }, withCredentials: true })).data;
    }

    /** Função para atualizar um benefício */
    async put(beneficio, memoriaCalculo) {
        let form = new FormData();
        let newBeneficio = { ...beneficio }

        delete newBeneficio.id;
        delete newBeneficio.memoriaCalculo;
        newBeneficio.tipoBeneficio = newBeneficio.tipoBeneficio.toUpperCase();

        form.append("beneficio", JSON.stringify(newBeneficio));
        form.append("memoriaCalculo", memoriaCalculo);

        return (await axios.put(`/beneficio/${beneficio.id}`, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    /** Função para excluir um benefício pelo seu ID */
    async delete(idBeneficio) {
        return (await axios.delete(`/beneficio/${idBeneficio}`, { withCredentials: true })).data;
    }
}

export default new BeneficioService();