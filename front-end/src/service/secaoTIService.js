import axios from "./api";

/** Classe service para as seções de TI */
class SecaoTIService {
    
    /** Função para buscar todas as seções de TI salvas */
    async getAll() {
        return (await axios.get(`/secao_ti`)).data;
    }
}

export default new SecaoTIService();