import axios from "./api";

const notificacaoPath = "/notificacoes";

class NotificacaoService {
    
    async getByUserId(userId) {
        return (await axios.get(`${notificacaoPath}/user/${userId}`)).data;
    }
}

export default new NotificacaoService();