import axios from "./api";

const pautaPath = "/pauta";

class PautaService {
  async get() {
    return (await axios.get(pautaPath)).data;
  }

  async put(pauta) {
    pauta.numeroSequencial = 44;
    console.log("auta: ", JSON.stringify(pauta));
    return (
      await axios.put(pautaPath + `/${pauta.id}`, JSON.stringify(pauta), {
        headers: {
          "content-type": "application/json",
        },
      })
    ).data;
  }
}

export default new PautaService();
