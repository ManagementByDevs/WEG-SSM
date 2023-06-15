import axios from "./api";

const ataPath = "/ata";

class AtaService {
  async getAtas() {
    return (await axios.get(`${ataPath}`)).data;
  }

  async getPage(params, page) {

    let newParams = {};
    if (params.titulo) {
      newParams.titulo = params.titulo;
    }
    if (params.numeroSequencial) {
      newParams.numeroSequencial = params.numeroSequencial;
    }
    if (params.apreciada) {
      newParams.publicadaDg = true;
    }
    if (params.naoApreciada) {
      newParams.publicadaDg = false;
    }
    if (params.publicada) {
      newParams.publicada = true;
    }
    if (params.naoPublicada) {
      newParams.publicada = false;
    }

    return (
      await axios.get(`${ataPath}/page?${page}`, {
        params: newParams,
        headers: { "Content-Type": "multipart/form-data" },
        withCredentials: true
      })
    ).data;
  }

  async post(pauta) {
    return (await axios.post(`${ataPath}`, pauta, { withCredentials: true })).data;
  }

  async put(ata, idAta) {
    return (await axios.put(`${ataPath}/${idAta}`, ata, { withCredentials: true })).data;
  }
}

export default new AtaService();
