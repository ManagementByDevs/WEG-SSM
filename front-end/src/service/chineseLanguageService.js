/**
 * Service para formatações de escritas chinesas, que não podem ser facilmente traduzidas
 */
class ChineseLanguageService {

    /**
     * Função para formatar um dígito de um número para o padrão chinês
     * @param {int} number 
     * @returns Dígito numeral formatado para chinês
     */
    formatSingularNumber(number) {
        switch (number) {
            case 0:
                return "零";
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
        }
    }

    /**
     * Função para formatar um número para chinês
     * @param {int} numero 
     * @returns Número traduzido para chinês
     */
    formatNumber(numero) {
        let numeroFinal = "";

        if (numero == 0) {
            return "零"
        }
        if (numero >= 1000000000) {
            if (parseInt(numero / 1000000000) == 1) {
                numeroFinal += "十亿";
                numero %= 1000000000;
            } else {
                numeroFinal += this.formatSingularNumber(parseInt(numero / 1000000000)) + "十亿";
                numero %= 1000000000;
            }
        }
        if (numero >= 100000000) {
            if (parseInt(numero / 100000000) == 1) {
                numeroFinal += "亿";
                numero %= 100000000;
            } else {
                numeroFinal += this.formatSingularNumber(parseInt(numero / 100000000)) + "亿";
                numero %= 100000000;
            }
        }
        if (numero >= 10000000) {
            numeroFinal += this.formatSingularNumber(parseInt(numero / 10000000)) + "千万";
            numero %= 10000000;
        }
        if (numero >= 1000000) {
            numeroFinal += this.formatSingularNumber(parseInt(numero / 1000000)) + "百万";
            numero %= 1000000;
        }
        if (numero >= 100000) {
            if (parseInt(numero / 100000) == 1) {
                numeroFinal += "十万";
                numero %= 100000;
            } else {
                numeroFinal += this.formatSingularNumber(parseInt(numero / 100000)) + "十万";
                numero %= 100000;
            }
        }
        if (numero >= 10000) {
            if (parseInt(numero / 10000) == 1) {
                numeroFinal += "万";
                numero %= 10000;
            } else {
                numeroFinal += this.formatSingularNumber(parseInt(numero / 10000)) + "万";
                numero %= 10000;
            }
        }
        if (numero >= 1000) {
            numeroFinal += this.formatSingularNumber(parseInt(numero / 1000)) + "千";
            numero %= 1000;
        }
        if (numero >= 100) {
            numeroFinal += this.formatSingularNumber(parseInt(numero / 100)) + "百";
            numero %= 100;
        }
        if (numero >= 10) {
            if (parseInt(numero / 10) == 1) {
                numeroFinal += "十";
                numero %= 10;
            } else {
                numeroFinal += this.formatSingularNumber(parseInt(numero / 10)) + "十";
                numero %= 10;
            }
        }
        if (numero > 0) {
            numeroFinal += this.formatSingularNumber(numero);
        }
        return numeroFinal;
    }

    /**
     * Função para formatar um ano para chinês
     * @param {string} ano 
     * @returns Ano formatado para chinês
     */
    formatAno(ano) {
        let anoFinal = "";
        let numeros = ano.split("");

        for (let letra of numeros) {
            anoFinal += this.formatSingularNumber(parseInt(letra));
        }
        anoFinal += "年";
        return anoFinal;
    }

    /**
     * Função para formatar um mês para chinês
     * @param {string} mes 
     * @returns Mês formatado para chinês
     */
    formatMes(mes) {
        return this.formatNumber(parseInt(mes)) + "月";
    }

    /**
     * Função para formatar um dia do mês para chinês
     * @param {string} dia 
     * @returns Dia formatado para chinês
     */
    formatDia(dia) {
        return this.formatNumber(parseInt(dia)) + "号";
    }

    /**
     * Função para formatar uma hora para chinês
     * @param {string} hora 
     * @returns Hora formatada para chinês
     */
    formatHora(hora) {
        let arrayHora = hora.split("-");
        let horaFinal = "";

        horaFinal += this.formatNumber(parseInt(arrayHora[0]));
        horaFinal += "点";
        horaFinal += this.formatNumber(parseInt(arrayHora[1]));
        return horaFinal;
    }

    /**
     * Função para formatar uma data completa para chinês, utilizando as outras funções de conversão
     * @param {Date} data 
     * @returns Data completa formatada para chinês
     */
    formatarDataCompleta(data) {
        let dataString = data.toString()
        let arrayData = dataString.split("/");

        let ano = "";
        if (arrayData[0] != "null") {
            ano = this.formatAno(arrayData[0]);
        }
        const mes = this.formatMes(arrayData[1]);
        const dia = this.formatDia(arrayData[2]);

        let hora = "";
        if (arrayData[3] != "null") {
            hora = this.formatHora(arrayData[3]);
            hora = " - " + hora;
        }

        return ano + mes + dia + hora;
    }
}

export default new ChineseLanguageService();