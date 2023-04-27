import axios from "./api";

/** Classe service para as BUs */
class BuService {

    /** Função para buscar todas as BUs salvas no banco */
    async getAll() {
        return (await axios.get(`/bu`, { withCredentials: true })).data;
    }
}

export default new BuService();