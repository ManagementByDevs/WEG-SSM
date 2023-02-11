import axios from "./api";

const usuario = "/usuario";

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
    return (await axios.get(usuario + `/login/${email}/${senha}`)).data;
  }

  async getUsuarioById(id) {
    return (await axios.get(usuario + `/${id}`)).data;
  }

  async getUsuarioByNomeAndTipo(nome, tipo) {
    return (await axios.get(usuario + `/filtragem/${nome}/${tipo}`)).data;
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
        headers: { "Content-Type": "application/json" },
      })
    ).data;
  }

  /**
   * Pega as preferências do usuário logado
   * @returns {Object} Preferências do usuário
   */
  getPreferencias() {
    let user = JSON.parse(localStorage.getItem("user"));
    let preferencias = "";

    if (user) {
      preferencias = user.preferencias;
    }

    if (preferencias == "") {
      return {
        themeMode: "light",
        fontSizeDefault: "14px",
        itemsVisualizationMode: "grid",
        lang: "pt",
      };
    }
    return JSON.parse(preferencias.slice(1, preferencias.length - 1));
  }

  /**
   * Retorna o usuário logado
   * @returns {Object} Usuário logado
   */
  getUser() {
    return JSON.parse(localStorage.getItem("user"));
  }

  //   updateUserInLocalStorage() {
  //     let user = this.getUser();
  //     this.getUsuarioById(user.id).then((response) => {
  //       localStorage.setItem("user", JSON.stringify(response));
  //     });
  //   }
}

export default new UsuarioService();
