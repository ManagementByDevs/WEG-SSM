import api from "./api";

const url = "/chat";

export const MensagemService = {
    getMensagensChat: async (id) => {
        try {
            const response = await api.get(url + "/" + id);
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    },

    postMensagem: async (mensagem) => {
        try {
            const response = await api.post(url, mensagem);
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    }
};