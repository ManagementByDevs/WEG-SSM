import axios from "./api";

const url = "/chat";

class chatService {
  async post(chat) {
    return (await axios.post(url, chat, { withCredentials: true })).data;
  }

  async getByPropostaAndUser(idProposta, idUser) {
    return (await axios.get(`${url}/user/${idUser}/proposta/${idProposta}`, { withCredentials: true })).data;
  }

  async getByRemetente(id) {
    return (await axios.get(`${url}/remetente/${id}`, { withCredentials: true })).data;
  }
}

export default new chatService();
