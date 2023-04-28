import Cookies from 'js-cookie';

/** Service para funções envolvendo cookies de autenticação */
class CookieService {

    /** Função para buscar o cookie salvo no navegador e chamar "decriptarToken" ou "decriptarUser" para decriptá-lo */
    getCookie(nome) {
        const cookieEncriptado = Cookies.get(nome);
        if (nome == "jwt") {
            return this.decriptarToken(cookieEncriptado);
        } else {
            return this.decriptarUser(cookieEncriptado);
        }
    }

    /** Função para decriptar um cookie recebido para um token JPA */
    decriptarToken(cookieEncriptado) {
        try {
            const parts = cookieEncriptado.split('.');
            if (parts.length !== 3) {
                throw new Error('Invalid JWT token');
            }
            const payload = parts[1];
            return JSON.parse(atob(payload));
        } catch (error) {
            return null;
        }
    }

    /** Função para decriptar um cookie recebido para um usuário */
    decriptarUser(cookieEncriptado) {
        try {
            return JSON.parse(decodeURIComponent(cookieEncriptado));
        } catch (error) {
            return null;
        }
    }

    /** Função para retornar o usuário autenticado no momento */
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