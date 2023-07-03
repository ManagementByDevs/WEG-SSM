import ChineseLanguageService from "./chineseLanguageService";

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

  getDateValue(data) {
    const dataNova = new Date(data);
    const dd = String(dataNova.getDate()).padStart(2, "0");
    const mm = String(dataNova.getMonth() + 1).padStart(2, "0");
    const yyyy = dataNova.getFullYear();

    return yyyy + "-" + mm + "-" + dd
  }

  /**
   * Retona data passada por parâmetro formatada de acordo com a linguagem recebida, não importando se é um Objeto Date ou String MySQL Date, 
   * se não passado nada, retorna a data de hoje no mesmo formato
   * @returns Data formatada de acordo com a linguagem
   */
  getTodaysDateUSFormat(linguagem) {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, "0");
    const mm = String(today.getMonth() + 1).padStart(2, "0");
    const yyyy = today.getFullYear();
    console.log(linguagem);

    switch (linguagem) {
      case "pt":
        return dd + "/" + mm + "/" + yyyy;
      case "en":
        return mm + "/" + dd + "/" + yyyy;
      case "es":
        return dd + "/" + mm + "/" + yyyy;
      case "ch":
        return ChineseLanguageService.formatarDataCompleta(yyyy + "/" + mm + "/" + dd + "/null");
      default:
        return yyyy + "-" + mm + "-" + dd;
    }
  }

  /** Função para retornar uma data formatada de acordo com a linguagem,
   *  sem as horas presentes
   */
  getOnlyDate(date, linguagem) {
    const newDate = new Date(date);
    const dd = String(newDate.getDate()).padStart(2, "0");
    const mm = String(newDate.getMonth() + 1).padStart(2, "0");
    const yyyy = newDate.getFullYear();

    switch (linguagem) {
      case "pt":
        return dd + "/" + mm + "/" + yyyy;
      case "en":
        return mm + "/" + dd + "/" + yyyy;
      case "es":
        return dd + "/" + mm + "/" + yyyy;
      case "ch":
        return ChineseLanguageService.formatarDataCompleta(yyyy + "/" + mm + "/" + dd + "/null");
      default:
        return dd + "/" + mm + "/" + yyyy;
    }
  }

  /**
   * Retona data passada por parâmetro formatada no padrão americano
   * @returns Data no padrão americano
   */
  getTodaysDateUSFormat(date, linguagem) {
    const newDate = new Date(date);
    const dd = String(newDate.getDate()).padStart(2, "0");
    const mm = String(newDate.getMonth() + 1).padStart(2, "0");
    const yyyy = newDate.getFullYear();

    let hora = newDate.getHours();
    let minutos = newDate.getMinutes();

    if (hora.toString().length == 1) {
      hora = "0" + hora.toString();
    }
    if (minutos.toString().length == 1) {
      minutos = "0" + minutos.toString();
    }

    switch (linguagem) {
      case "pt":
        return dd + "/" + mm + "/" + yyyy + " - " + hora + ":" + minutos;
      case "en":
        return mm + "/" + dd + "/" + yyyy + " - " + hora + ":" + minutos;
      case "es":
        return dd + "/" + mm + "/" + yyyy + " - " + hora + ":" + minutos;
      case "ch":
        return ChineseLanguageService.formatarDataCompleta(yyyy + "/" + mm + "/" + dd + "/" + hora + "-" + minutos);
      default:
        return dd + "/" + mm + "/" + yyyy + " - " + hora + ":" + minutos;
    }
  }

  /**
   * Retorna a data passada por parâmetro com padrão MySQL formatada por uma linguagem recebida
   * @param {DateMySQL} date
   * @returns Data formatada de acordo com a linguagem
   */
  getFullDateUSFormat(date, linguagem) {
    const newDate = new Date(date);
    const dd = String(newDate.getDate()).padStart(2, "0");
    const mm = String(newDate.getMonth() + 1).padStart(2, "0");
    const yyyy = newDate.getFullYear();
    const hh = String(newDate.getHours()).padStart(2, "0");
    const min = String(newDate.getMinutes()).padStart(2, "0");

    switch (linguagem) {
      case "pt":
        return dd + "/" + mm + "/" + yyyy + " " + hh + ":" + min;
      case "en":
        return mm + "/" + dd + "/" + yyyy + " " + hh + ":" + min;
      case "es":
        return dd + "/" + mm + "/" + yyyy + " " + hh + ":" + min;
      case "ch":
        return ChineseLanguageService.formatarDataCompleta(yyyy + "/" + mm + "/" + dd + "/" + hh + "-" + min);
      default:
        return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + min;
    }
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

    return `${dataFormatada.getHours()}:${dataFormatada.getMinutes() < 10
      ? "0" + dataFormatada.getMinutes()
      : dataFormatada.getMinutes()
      }
    `;
  };

  formatarDataRequisicao = (data) => {
    data = new Date(data);
    return data.toISOString();
  }
}

export default new DateService();
