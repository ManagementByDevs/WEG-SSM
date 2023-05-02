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
   * Retona data passada por parâmetro formatada no padrão americano, não importando se é um Objeto Date ou String MySQL Date, se não passado nada, retorna a data de hoje no mesmo formato
   * @returns Data no padrão americano
   */
  getTodaysDateUSFormat() {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, "0");
    const mm = String(today.getMonth() + 1).padStart(2, "0");
    const yyyy = today.getFullYear();
    return yyyy + "-" + mm + "-" + dd;
  }

  /**
   * Retona data passada por parâmetro formatada no padrão americano
   * @returns Data no padrão americano
   */
  getTodaysDateUSFormat(date) {
    const newDate = new Date(date);
    const dd = String(newDate.getDate()).padStart(2, "0");
    const mm = String(newDate.getMonth() + 1).padStart(2, "0");
    const yyyy = newDate.getFullYear();
    return yyyy + "-" + mm + "-" + dd;
  }

  /**
   * Retorna a data passada por parâmetro com padrão MySQL para o padrão americano
   * @param {DateMySQL} date
   * @returns Data no padrão americano com hora
   */
  getFullDateUSFormat(date) {
    const newDate = new Date(date);
    const dd = String(newDate.getDate()).padStart(2, "0");
    const mm = String(newDate.getMonth() + 1).padStart(2, "0");
    const yyyy = newDate.getFullYear();
    const hh = String(newDate.getHours()).padStart(2, "0");
    const min = String(newDate.getMinutes()).padStart(2, "0");

    return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + min;
  }

  /**
   * Retorna um objeto do tipo Date a partir de um registro do tipo Date do MySQL
   * @param {*} date
   * @returns
   */
  getDateByMySQLFormat(dataMysql) {
    if (!dataMysql) {
      return "";
    }

    let date = dataMysql.replace(/[-]/g, "/").replace(/[T]/g, " ");
    date = Date.parse(date);
    return new Date(date);
  }

  /**
   * Retorna as horas em String
   * @param {*} date
   * @returns
   */
  getHorasFormatado = (data) => {
    let dataFormatada = new Date(this.getDateByMySQLFormat(data));

    return `${dataFormatada.getHours()}:${
      dataFormatada.getMinutes() < 10
        ? "0" + dataFormatada.getMinutes()
        : dataFormatada.getMinutes()
    }
    `;
  };
}

export default new DateService();
