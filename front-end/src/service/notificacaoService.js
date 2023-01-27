import axios from "./api";

const notificacaoPath = "/notificacoes";

class NotificacaoService {
    
    async getAll(userId) {
        return (await axios.get(`${notificacaoPath}/${userId}`)).data;
    }
}

export default new NotificacaoService();