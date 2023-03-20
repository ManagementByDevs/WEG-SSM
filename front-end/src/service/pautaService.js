import axios from "./api";

const pautaPath = "/pauta";

class PautaService {
  async get() {
    return (await axios.get(pautaPath)).data;
  }

  async getPage(params, page) {
    return (await axios.get(pautaPath + `/page?${page}`, { params })).data;
  }

  async getById(id) {
    return (await axios.get(pautaPath + `/id/${id}`)).data;
  }

  async put(pauta) {
    return (
      await axios.put(pautaPath + `/${pauta.id}`, JSON.stringify(pauta), {
        headers: {
          "content-type": "application/json",
        },
      })
    ).data;
  }

  async post(pauta) {
    return (
      await axios.post(pautaPath, JSON.stringify(pauta), {
        headers: {
          "content-type": "application/json",
        },
      })
    ).data;
  }

  async delete(id) {
    return (await axios.delete(pautaPath + `/${id}`)).data;
  }

  /**
   * Retorna um objeto pauta com uma proposta
   * @param {*} numeroSequencial
   * @param {*} dataReuniao
   * @param {*} comissao
   * @param {*} analistaResponsavel
   * @param {*} propostas
   * @returns
   */
  createPautaObjectWithPropostas(
    numeroSequencial,
    dataReuniao,
    comissao,
    idAnalistaResponsavel,
    propostas
  ) {
    return {
      numeroSequencial,
      dataReuniao,
      comissao: { idForum: comissao.idForum },
      analistaResponsavel: { id: idAnalistaResponsavel },
      propostas,
    };
  }
}

export default new PautaService();
