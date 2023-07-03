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

  async getByDemanda(id) {
    return (
      await axios.get(proposta + `/demanda/${id}`, { withCredentials: true })
    ).data;
  }

  async getByPPM(ppm) {
    return (
      await axios.get(proposta + `/ppm/${ppm}`, { withCredentials: true })
    ).data;
  }

  async getPage(params, page) {
    let newParams = {};

    if (params.titulo != null) {
      newParams.titulo = params.titulo;
    }
    if (params.departamento != null) {
      newParams.departamento = JSON.stringify(params.departamento);
    }
    if (params.forum != null) {
      newParams.forum = JSON.stringify(params.forum);
    }
    if (params.gerente != null) {
      newParams.gerente = JSON.stringify(params.gerente);
    }
    if (params.solicitante != null) {
      newParams.solicitante = JSON.stringify(params.solicitante);
    }
    if (params.analista != null) {
      newParams.analista = JSON.stringify(params.analista);
    }
    if (params.status != null) {
      newParams.status = params.status;
    }
    if (params.tamanho != null) {
      newParams.tamanho = params.tamanho;
    }
    if (params.presenteEm != null) {
      newParams.presenteEm = params.presenteEm;
    }
    if (params.codigoPPM != null) {
      newParams.codigoPPM = params.codigoPPM;
    }

    return (
      await axios.get(proposta + `/page?${page}`, {
        params: newParams,
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
  }

  async post(proposta) {
    let form = new FormData();

    form.append("proposta", JSON.stringify(proposta));
    return (
      await axios.post(`/proposta`, form, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
  }

  async atualizarStatus(idProposta, statusNovo, motivoRecusa) {
    let form = new FormData();
    form.append("status", statusNovo);
    if (motivoRecusa) {
      form.append("motivo", motivoRecusa);
    }

    return (
      await axios.put(`/proposta/status/${idProposta}`, form, {
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

  async removerPresenca(idProposta) {
    return (
      await axios.put(`${proposta}/presente/${idProposta}`, {
        withCredentials: true,
      })
    ).data;
  }

  async atualizacaoPauta(idProposta, publicada) {
    let form = new FormData();
    form.set("publicada", publicada);

    return (
      await axios.put(`${proposta}/pauta/${idProposta}`, form, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
  }

  async atualizacaoAta(idProposta, parecerComissao, parecerInformacao) {
    let form = new FormData();
    form.set("parecerComissao", parecerComissao);
    form.set("parecerInformacao", parecerInformacao);

    return (
      await axios.put(`${proposta}/ata/${idProposta}`, form, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
  }

  async atualizacaoDg(idProposta, parecerComissao, parecerInformacao) {
    let form = new FormData();
    form.set("parecerComissao", parecerComissao);
    form.set("parecerInformacao", parecerInformacao);

    return (
      await axios.put(`${proposta}/dg/${idProposta}`, form, {
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true,
      })
    ).data;
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
    novosBeneficios = []
  ) {
    let form = new FormData();
    let propostaNovosDados = { ...propostaObj };

    propostaNovosDados.beneficios = novosBeneficios;
    propostaNovosDados.tabelaCustos = novasTabelasCusto;

    form.append("proposta", JSON.stringify(propostaObj));
    form.append("propostaComDadosNovos", JSON.stringify(propostaNovosDados));

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
