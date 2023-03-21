import axios from "./api";

const url = "/login"

/** Serviço para autenticação de um usuário usando o banco de dados */
class AutenticacaoService {

    /** Função principal de login */
    login(user) {
        const config = {
            withCredentials: true
        };
        return axios.post(url + "/auth", user, config);
    }
}

export default new AutenticacaoService();