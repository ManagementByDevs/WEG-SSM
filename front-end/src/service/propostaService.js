import axios from "./api";

import EntitiesObjectService from "./entitiesObjectService";

const proposta = "/proposta";

class PropostaService {
  async get() {
    return (await axios.get(proposta, { withCredentials: true })).data;
  }

  async getById(id) {
    return (await axios.get(proposta + "/" + id, { withCredentials: true }))
      .data;
  }

  async getByPPM(ppm) {
    return (
      await axios.get(proposta + `/ppm/${ppm}`, { withCredentials: true })
    ).data;
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
        withCredentials: true,
      })
    ).data;
  }

  async post(proposta, listaIds, escopoTexto) {
    let form = new FormData();

    form.append("proposta", JSON.stringify(proposta));
    for (let id of listaIds) {
      form.append("idsAnexos", id);
    }
    form.append("escopo", escopoTexto);

    return (
      await axios.post(`/proposta`, form, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
  }

  async atualizarStatus(idProposta, statusNovo) {
    return (
      await axios.put(`/proposta/status/${idProposta}/${statusNovo}`, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
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
          withCredentials: true,
        })
      ).data;
    } else {
      return (
        await axios.put(`/proposta/sem-arquivos`, form, {
          headers: { "Content-Type": "multipart/form-data" },
          withCredentials: true,
        })
      ).data;
    }
  }

  async putWithoutArquivos(propostaObj, idProposta) {
    return (
      await axios.put(
        `${proposta}/${idProposta}`,
        JSON.stringify(propostaObj),
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: true,
        }
      )
    ).data;
  }

  async putComNovosDados(
    propostaObj = EntitiesObjectService.proposta(),
    idProposta,
    novasTabelasCusto = [],
    novosBeneficios = [],
    listaIdsAnexos = [],
    escopoTexto
  ) {
    let form = new FormData();
    let propostaNovosDados = { ...propostaObj };

    propostaNovosDados.beneficios = novosBeneficios;
    propostaNovosDados.tabelaCustos = novasTabelasCusto;

    form.append("proposta", JSON.stringify(propostaObj));
    form.append("propostaComDadosNovos", JSON.stringify(propostaNovosDados));
    form.append("escopo", escopoTexto);

    for (let idAnexo of listaIdsAnexos) {
      form.append("listaIdsAnexos", idAnexo);
    }

    return (
      await axios.put(`${proposta}/update-novos-dados/${idProposta}`, form, {
        withCredentials: true,
      })
    ).data;
  }

  async addHistorico(idProposta, texto, documento, usuario) {
    let form = new FormData();
    form.set("acao", texto);
    form.set("documento", documento);
    form.set("usuarioId", usuario);

    return (
      await axios.put(`/proposta/add-historico/${idProposta}`, form, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
  }
}

export default new PropostaService();
