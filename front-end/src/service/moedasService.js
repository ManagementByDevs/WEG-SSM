import axios from 'axios';

const api = axios.create({
    baseURL: "https://economia.awesomeapi.com.br/json/last",
})

class MoedasService {
    async getDolar() {
        return (
            await api.get("/USD-BRL", {
                headers: { "Content-Type": "application/json" },

            })
        ).data;
    }

    async getEuro() {
        return (
            await api.get("/EUR-BRL", {
                headers: { "Content-Type": "application/json" },

            })
        ).data;
    }
}

export default new MoedasService();
