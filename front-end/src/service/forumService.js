import axios from "./api";

/** Classe service para os fóruns */
class ForumService {

    /** Função para buscar todos os fóruns salvos */
    async getAll() {
        return (await axios.get(`/forum`, { withCredentials: true })).data;
    }
}

export default new ForumService();