import axios from "./api";

const url = "/mensagem";

export const MensagemService = {
    getMensagensChat: async (id) => {
        try {
            const response = await axios.get(url + "/chat/" + id, { withCredentials: true });
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    },

    postMensagem: async (mensagem) => {
        try {
            const response = await axios.post(url, mensagem, { withCredentials: true });
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    }
};