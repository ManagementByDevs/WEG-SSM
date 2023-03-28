import axios from "./api";

const anexoPath = "/anexo";

/** Classe service de anexos */
class AnexoService {

    /** Função para salvar um anexo, recebendo um arquivo File como parâmetro */
    async save(anexo) {
        let form = new FormData();
        form.set("anexo", anexo);
        return (await axios.post(anexoPath, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }

    /** Função para excluir um anexo pelo seu ID */
    async deleteById(idAnexo) {
        return (await axios.delete(`${anexoPath}/${idAnexo}`)).data;
    }

    /** Função para excluir um anexo pelo seu nome */
    async delete(nomeAnexo) {
        return (await axios.delete(`${anexoPath}/nome/${nomeAnexo}`)).data;
    }

}

export default new AnexoService();