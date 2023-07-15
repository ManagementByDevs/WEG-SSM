import axios from 'axios';

const api = axios.create({
    baseURL: "http://localhost:5000/api",
})

class DemandaSimilaridade {
    async postSimilaridade(demanda) {
        return (await api.post(`/similaridade`, demanda,  {headers: { "Content-Type": "application/json" }},)).data;
    }
}

export default new DemandaSimilaridade();