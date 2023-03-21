import axios from "./api";

/** Classe service para os responsáveis de negócio */
class ResponsavelNegocioService {

    /** Função para criar um novo responsável de negócio */
    async post(responsavelNegocio) {
        return (await axios.post(`/responsavel_negocio`, responsavelNegocio, { headers: { "Content-Type": "application/json" } })).data;
    }

    /** Função para atualizar um responsável de negócio */
    async put(responsavelNegocio) {
        return (await axios.put(`/responsavel_negocio`, responsavelNegocio, { headers: { "Content-Type": "application/json" } })).data;
    }

    /** Função para excluir um responsável de negócio pelo seu ID */
    async delete(responsavelNegocioId) {
        return (await axios.delete(`/responsavel_negocio/${responsavelNegocioId}`)).data;
    }

}

export default new ResponsavelNegocioService();