import axios from "./api";

const anexoPath = "/anexo";

class AnexoService {
    
    async delete(idAnexo) {
        return (await axios.delete(`/anexo/nome/${idAnexo}`)).data;
    }

    async deleteById(idAnexo) {
        return (await axios.delete(`${anexoPath}/${idAnexo}`)).data;
    }
    
}

export default new AnexoService();