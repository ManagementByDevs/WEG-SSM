import axios from "./api";
import DateService from "./dateService";

const notificacaoPath = "/notificacoes";

class NotificacaoService {
  aprovado = "APROVADO";
  reprovado = "REPROVADO";
  maisInformacoes = "MAIS_INFORMACOES";
  mensagens = "MENSAGENS";

  async getByUserId(userId, page) {
    return (await axios.get(`${notificacaoPath}/user/${userId}?${page}`, { withCredentials: true })).data;
  }

  async getByDate(date) {
    return (await axios.get(`${notificacaoPath}/date/${date}`, { withCredentials: true })).data;
  }

  async getByUserIdAndNotVisualizado(userId) {
    return (
      await axios.get(`${notificacaoPath}/user/modal-notificacao/${userId}`, { withCredentials: true })
    ).data;
  }

  async post(notificacao) {
    return (await axios.post(`${notificacaoPath}`, notificacao, { withCredentials: true })).data;
  }

  async put(notificacao) {
    return (await axios.put(`${notificacaoPath}`, notificacao, { withCredentials: true })).data;
  }

  async delete(id) {
    return (await axios.delete(`${notificacaoPath}/${id}`, { withCredentials: true })).data;
  }

  /**
   * Cria e retorna um objeto notificação
   * @param {APROVADO, REPROVADO, MAIS_INFORMACOES, MENSAGENS} tipoNotificacao
   * @returns Notificação Object
   */
  createNotificationObject(tipoNotificacao, demanda) {
    let statusNotificacao = this.fillStatusNotification(tipoNotificacao);
    let titulo =
      "A demanda de número sequencial " +
      demanda.id +
      " foi " +
      statusNotificacao;
    return {
      titulo,
      data: DateService.getTodaysDateMySQL(),
      tipoNotificacao: tipoNotificacao,
      visualizado: false,
      usuario: { id: demanda.solicitante.id },
    };
  }

  /**
   * Retorna o status da notificação formatado
   * @param {APROVADO, REPROVADO, MAIS_INFORMACOES, MENSAGENS} tipoNotificacao
   * @returns
   */
  fillStatusNotification(tipoNotificacao) {
    switch (tipoNotificacao) {
      case "APROVADO":
        return "aprovada";
      case "REPROVADO":
        return "reprovada";
      case "MAIS_INFORMACOES":
        return "reprovada por falta de informações";
      case "MENSAGENS":
        return "você possui novas mensagens";
    }
  }
}

export default new NotificacaoService();
