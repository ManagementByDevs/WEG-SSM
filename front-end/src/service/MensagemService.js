import {Axios} from "./Axios";

const url = "/chat";

export const MensagemService = {
    getMensagensChat: async (id) => {
        try {
            const response = await Axios.get(url + "/" + id);
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    },

    postMensagem: async (mensagem) => {
        try {
            const response = await Axios.post(url, mensagem);
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    }
};

export default new MensagemService();