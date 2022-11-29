import axios from "./api";

const demanda = "/demanda";

class DemandaService {

    async getPage(params, page) {

        const form = new FormData();
        // if (params.departamento != null) {
        //     form.set("departamento", params.departamento);
        // }
        // if (params.forum != null) {
        //     form.set("forum", params.forum);
        // }
        // if (params.gerente != null) {
        //     form.set("gerente", params.gerente);
        // }
        console.log(params.solicitante)
        if (params.solicitante != null) {
            console.log("aaaaaaaa")
        }
        form.append("solicitante", JSON.stringify(params.solicitante));

        // if (params.status != null) {
        //     form.set("status", params.status);
        // }
        // if (params.tamanho != null) {
        //     form.set("tamanho", params.tamanho);
        // }
        // if (params.titulo != null) {
        //     form.set("titulo", params.titulo);
        // }

        console.log(form.getAll("solicitante"));
        return (await axios.get(demanda + `/page`, form, { headers: { "Content-Type": "multipart/form-data" } })).data;
    }
}

export default new DemandaService();