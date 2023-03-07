import axios from "./api";

const pautaPath = "/pauta";

class PautaService {
  async get() {
    return (await axios.get(pautaPath)).data;
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

  /**
   * Retorna um objeto pauta com uma proposta
   * @param {*} numeroSequencial
   * @param {*} dataReuniao
   * @param {*} comissao
   * @param {*} proposta
   * @returns
   */
  createPautaObjectWithPropostas(
    numeroSequencial,
    dataReuniao,
    comissao,
    propostas
  ) {
    return { numeroSequencial, dataReuniao, comissao, propostas };
  }
}

export default new PautaService();
