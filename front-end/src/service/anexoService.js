import axios from "./api";

const anexoPath = "/anexo";

/** Classe service de anexos */
class AnexoService {

    /** Função para excluir um anexo pelo seu ID */
    async deleteById(idAnexo) {
        return (await axios.delete(`${anexoPath}/${idAnexo}`)).data;
    }

    /** Função para excluir um anexo pelo seu nome */
    async delete(nomeAnexo) {
        return (await axios.delete(`/anexo/nome/${nomeAnexo}`)).data;
    }

}

export default new AnexoService();