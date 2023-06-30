class ChineseLanguageService {

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

    formatAno(ano) {
        let anoFinal = "";
        let numeros = ano.split("");

        for (let letra of numeros) {
            anoFinal += this.formatSingularNumber(parseInt(letra));
        }
        anoFinal += "年";
        return anoFinal;
    }

    formatMes(mes) {
        return this.formatNumber(parseInt(mes)) + "月";
    }

    formatDia(dia) {
        return this.formatNumber(parseInt(dia)) + "号";
    }

    formatHora(hora) {
        let arrayHora = hora.split("-");
        let horaFinal = "";

        horaFinal += this.formatNumber(parseInt(arrayHora[0]));
        horaFinal += "点";
        horaFinal += this.formatNumber(parseInt(arrayHora[1]));
        return horaFinal;
    }

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