import axios from "./api";

const notificacaoPath = "/notificacoes";

class NotificacaoService {
    
    async getByUserId(userId) {
        return (await axios.get(`${notificacaoPath}/user/${userId}`)).data;
    }

    async post(notificacao) {
        return (await axios.post(`${notificacaoPath}`, notificacao)).data;
    }

    async put(notificacao) {
        return (await axios.put(`${notificacaoPath}`, notificacao)).data;
    }

    async delete(id) {
        return (await axios.delete(`${notificacaoPath}/${id}`)).data;
    }
}

export default new NotificacaoService();