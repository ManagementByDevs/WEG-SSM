import axios from "./api";

const historicoPath = "/historico";

class HistoricoService {

    async post(idUsuario, historico) {
        return (await axios.post(`${historicoPath}/${idUsuario}`, historico, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

}

export default new HistoricoService();