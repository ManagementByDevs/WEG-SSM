import axios from "./api";

const usuario = "/usuario";

class UsuarioService {

    async login(email, senha) {
        return (await axios.get(usuario + `/login/${email}/${senha}`)).data;
    }

    async getUsuarioById(id) {
        return (await axios.get(usuario + `/${id}`)).data;
    }

    async getUsuarioByNomeAndTipo(nome, tipo) {
        return (await axios.get(usuario + `/filtragem/${nome}/${tipo}`)).data;
    }
}

export default new UsuarioService();