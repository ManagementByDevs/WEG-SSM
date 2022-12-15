import axios from "./api";

class AnexoService {
    
    async delete(idAnexo) {
        return (await axios.delete(`/anexo/nome/${idAnexo}`)).data;
    }
}

export default new AnexoService();