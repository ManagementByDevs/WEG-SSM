class DateService {

    /**
     * Chame para criar um objeto com a data de hoje
     * @returns Data de hoje
     */
    getTodaysDate() {
        return new Date();
    }

    /**
     * Retorna um new Date com a data passada por parâmetro
     * @param {Objeto Date} date 
     * @returns 
     */
    getDateByPreviousDate(date) {
        return new Date(date);
    }

    /**
     * Retorna data de hoje formatada no padrão MySQL
     * @returns Data no padrão MySQL
     */
    getTodaysDateMySQL() {
        return new Date().toISOString().slice(0, 19);
    }

    /**
     * Retonar data de hoje formatada no padrão americano
     * @returns Data no padrão americano
     */
    getTodaysDateUSFormat() {
        const today = new Date();
        const dd = String(today.getDate()).padStart(2, '0');
        const mm = String(today.getMonth() + 1).padStart(2, '0');
        const yyyy = today.getFullYear();
        return yyyy + '-' + mm + '-' + dd;
    }

    getTodaysDateUSFormat(date) {
        const dd = String(date.getDate()).padStart(2, '0');
        const mm = String(date.getMonth() + 1).padStart(2, '0');
        const yyyy = date.getFullYear();
        return yyyy + '-' + mm + '-' + dd;
    }
}

export default new DateService();