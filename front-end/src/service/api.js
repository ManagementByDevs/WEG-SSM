import axios from 'axios';

const api = axios.create({
    baseURL: "http://localhost:8443/weg_ssm",
})

export default api;