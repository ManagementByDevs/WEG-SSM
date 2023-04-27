import Cookies from 'js-cookie';

/** Service para funções envolvendo cookies de autenticação */
class CookieService {

    /** Função para buscar o cookie salvo no navegador e chamar "decriptarToken" para decriptá-lo */
    getCookie(nome) {
        const cookieEncriptado = Cookies.get(nome);
        let cookieDecriptado = null;
        if (nome == "jwt") {
            cookieDecriptado = this.decriptarToken(cookieEncriptado);
        } else {
            cookieDecriptado = this.decriptarUser(cookieEncriptado);
        }
        return cookieDecriptado;
    }

    /** Função para decriptar um cookie recebido para um token JPA */
    decriptarToken(cookieEncriptado) {
        try {
            const parts = cookieEncriptado.split('.');
            if (parts.length !== 3) {
                console.log('errp')
                throw new Error('Invalid JWT token');
            }
            const payload = parts[1];
            return JSON.parse(atob(payload));
        } catch (error) {
            return null;
        }
    }

    decriptarUser(cookieEncriptado) {
        try {
            return JSON.parse(decodeURIComponent(cookieEncriptado));
        } catch (error) {
            return null;
        }
    }

    getUser() {
        const cookie = this.getCookie("user");
        return cookie.usuario;
    }

    /** Função para retirar um cookie salvo, recebendo seu nome como parâmetro */
    limparCookie(nomeCookie) {
        Cookies.remove(nomeCookie);
    }

}

export default new CookieService();