import axios from "./api";

/** Classe service para os departamentos */
class DepartamentoService {

    /** Função para buscar todos os departamentos salvos */
    async getAll() {
        return (await axios.get(`/departamento`, { withCredentials: true })).data;
    }
}

export default new DepartamentoService();