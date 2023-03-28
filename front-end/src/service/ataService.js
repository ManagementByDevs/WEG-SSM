import axios from "./api";

const ataPath = "/ata";

class AtaService {
  async getAtas() {
    return (await axios.get(`${ataPath}`)).data;
  }

  async getPage(params, page) {
    return (
      await axios.get(`${ataPath}/page?${page}`, {
        params,
        headers: { "Content-Type": "application/json" },
      })
    ).data;
  }

  async post(pauta) {
    return (await axios.post(`${ataPath}`, pauta)).data;
  }

  async put(ata, idAta) {
    return (await axios.put(`${ataPath}/${idAta}`, ata)).data;
  }
}

export default new AtaService();
