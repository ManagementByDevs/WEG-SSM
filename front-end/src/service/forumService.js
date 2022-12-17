import axios from "./api";

class ForumService {
    
    async getAll() {
        return (await axios.get(`/forum`)).data;
    }
}

export default new ForumService();