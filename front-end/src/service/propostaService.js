import axios from "./api";

const proposta = "/proposta";

class PropostaService {
  async get() {
    return (await axios.get(proposta)).data;
  }

  async getById(id) {
    return (await axios.get(proposta + "/" + id)).data;
  }

  async getByPPM(ppm) {
    return (await axios.get(proposta + `/ppm/${ppm}`)).data;
  }

  async getPage(params, page) {
    if (params.departamento != null) {
      params.departamento = JSON.stringify(params.departamento);
    }
    if (params.forum != null) {
      params.forum = JSON.stringify(params.forum);
    }
    if (params.gerente != null) {
      params.gerente = JSON.stringify(params.gerente);
    }
    if (params.solicitante != null) {
      params.solicitante = JSON.stringify(params.solicitante);
    }
    if (params.analista != null) {
      params.analista = JSON.stringify(params.analista);
    }

    return (
      await axios.get(proposta + `/page?${page}`, {
        params: params,
        headers: { "Content-Type": "multipart/form-data" },
      })
    ).data;
  }

  async post(proposta, listaIds) {
    let form = new FormData();

    form.append("proposta", JSON.stringify(proposta));
    for (let id of listaIds) {
      form.append("idsAnexos", id);
    }

    return (await axios.post(`/proposta`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
  }

  async atualizarStatus(idProposta, statusNovo) {
    return (
      await axios.put(`/proposta/status/${idProposta}/${statusNovo}`, {
        headers: { "Content-Type": "multipart/form-data" },
      })
    ).data;
  }

  async put(proposta, arquivos) {
    let form = new FormData();

    form.append("proposta", JSON.stringify(proposta));
    for (let arquivo of arquivos) {
      form.append("anexos", arquivo);
    }

    if (arquivos.length > 0) {
      return (
        await axios.put(`/proposta`, form, {
          headers: { "Content-Type": "multipart/form-data" },
        })
      ).data;
    } else {
      return (
        await axios.put(`/proposta/sem-arquivos`, form, {
          headers: { "Content-Type": "multipart/form-data" },
        })
      ).data;
    }
  }

  async putWithoutArquivos(propostaObj, idProposta) {
    return (
      await axios.put(`${proposta}/${idProposta}`, JSON.stringify(propostaObj), {
        headers: { "Content-Type": "application/json" },
      })
    ).data;
  }

  async addHistorico(idProposta, texto, documento, usuario) {
    let form = new FormData();
    form.set("acao", texto);
    form.set("documento", documento);
    form.set("usuarioId", usuario);

    return (await axios.put(`/proposta/add-historico/${idProposta}`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
  }
}

export default new PropostaService();
