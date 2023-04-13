import axios from "./api";

const usuario = "/usuario";

/** Classe service do usuário */
class UsuarioService {
  /*
   * Preferências:
   *   {
   *   themeMode: "dark" | "light",
   *   fontSizeDefault: "14px" | "12px" | "16px" | "18px",
   *   itemsVisualizationMode: "grid" | "table",
   *   lang: "pt" | "en" | "ch",
   *   }
   */

  async login(email, senha) {
    return (await axios.get(usuario + `/login/${email}/${senha}`, { withCredentials: true })).data;
  }

  async getUsuarioById(id) {
    return (await axios.get(usuario + `/${id}`, { withCredentials: true })).data;
  }

  async getUsuarioByEmail(email) {
    return (await axios.get(usuario + `/email/${email}`, { withCredentials: true })).data;
  }

  async getUsuarioByNomeAndTipo(nome, tipo) {
    return (await axios.get(usuario + `/filtragem/${nome}/${tipo}`, { withCredentials: true })).data;
  }

  /**
   * Atualiza o usuário
   * @param {*} userId
   * @param {*} user
   * @returns
   */
  async updateUser(userId, user) {
    return (
      await axios.put(usuario + `/${userId}`, user, {
        headers: { "Content-Type": "application/json" }, withCredentials: true
      })
    ).data;
  }

  /**
   * Pega as preferências do usuário logado ou retorna as preferências padrão se não estiver logado
   * @returns {{themeMode: "dark" | "light", fontSizeDefault: "14px" | "12px" | "16px" | "18px", itemsVisualizationMode: "grid" | "table", lang: "pt" | "en" | "ch",}} Preferências do usuário
   */
  getPreferencias(email) {
    if(email) {
      return this.getUsuarioByEmail(email).then((user) => {
        let preferencias = "";
  
        if (user) {
          preferencias = user.preferencias;
        }
  
        if (!preferencias) {
          return {
            themeMode: "light",
            fontSizeDefault: "14px",
            itemsVisualizationMode: "grid",
            lang: "pt",
          };
        }
  
        return JSON.parse(preferencias);
      });
    }
  }
}

export default new UsuarioService();
