import axios from "./api";

const usuario = "/usuario";

class UsuarioService {

    async login(email, senha) {
        return (await axios.get(usuario + `/login/${email}/${senha}`)).data;
    }
}

export default new UsuarioService();