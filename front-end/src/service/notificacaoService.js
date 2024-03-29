import axios from "./api";
import DateService from "./dateService";

const notificacaoPath = "/notificacoes";

class NotificacaoService {
  aprovado = "APROVADO";
  reprovado = "REPROVADO";
  maisInformacoes = "MAIS_INFORMACOES";
  mensagens = "MENSAGENS";
  aprovadoGerente = "APROVADO_GERENTE";
  reprovadoGerente = "REPROVADO_GERENTE";
  criadoProposta = "CRIADO_PROPOSTA";

  aprovadoComissao = "APROVADO_COMISSAO";
  reprovadoComissao = "REPROVADO_COMISSAO";
  businessComissao = "BUSINESS_CASE_COMISSAO";
  maisInformacoesComissao = "MAIS_INFORMACOES_COMISSAO";

  aprovadoDG = "APROVADO_DG";
  reprovadoDG = "REPROVADO_DG";

  assessmentAnalista = "ASSESSMENT_ANALISTA";
  businessCaseAnalista = "BUSINESS_CASE_ANALISTA";
  cancelledAnalista = "CANCELLED_ANALISTA";
  doneAnalista = "DONE_ANALISTA";

  async getByUserId(userId, page) {
    return (
      await axios.get(`${notificacaoPath}/user/${userId}?${page}`, {
        withCredentials: true,
      })
    ).data;
  }

  async getByDate(date) {
    return (
      await axios.get(`${notificacaoPath}/date/${date}`, {
        withCredentials: true,
      })
    ).data;
  }

  async getByUserIdAndNotVisualizado(userId) {
    return (
      await axios.get(`${notificacaoPath}/user/modal-notificacao/${userId}`, {
        withCredentials: true,
      })
    ).data;
  }

  async post(notificacao) {
    return (
      await axios.post(`${notificacaoPath}`, notificacao, {
        withCredentials: true,
      })
    ).data;
  }

  async put(notificacao) {
    return (
      await axios.put(`${notificacaoPath}/${notificacao.id}`, notificacao, {
        withCredentials: true,
      })
    ).data;
  }

  async delete(id) {
    return (
      await axios.delete(`${notificacaoPath}/${id}`, { withCredentials: true })
    ).data;
  }

  /**
   * Cria e retorna um objeto notificação
   * @param {string} tipoNotificacao - "APROVADO", "REPROVADO", "MAIS_INFORMACOES", "MENSAGENS", "APROVADO_GERENTE", "REPROVADO_GERENTE"
   * @param {Demanda} demanda
   * @returns Notificação Object
   */
  createNotificationObject(tipoNotificacao, demanda, idRemetente) {
    let numeroSequencial = demanda.codigoPPM ? demanda.codigoPPM : demanda.id;
    return {
      numeroSequencial,
      data: DateService.getTodaysDateMySQL(),
      tipoNotificacao: tipoNotificacao,
      visualizado: false,
      usuario: { id: demanda.solicitante.id },
      remetente: { id: idRemetente },
    };
  }
}

export default new NotificacaoService();
