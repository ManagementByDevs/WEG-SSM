import axios from "./api";

const anexoPath = "/anexo";

/** Classe service de anexos */
class AnexoService {

    /** Função para salvar um anexo, recebendo um arquivo File como parâmetro */
    async save(anexo) {
        let form = new FormData();
        form.set("anexo", anexo);
        return (await axios.post(anexoPath, form, { headers: { "Content-Type": "multipart/form-data" }, withCredentials: true })).data;
    }

    /** Função para excluir um anexo pelo seu ID */
    async deleteById(idAnexo) {
        return (await axios.delete(`${anexoPath}/${idAnexo}`, { withCredentials: true })).data;
    }

}

export default new AnexoService();