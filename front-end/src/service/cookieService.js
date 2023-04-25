import Cookies from 'js-cookie';

/** Service para funções envolvendo cookies de autenticação */
class CookieService {

    /** Função para buscar o cookie salvo no navegador e chamar "decriptarToken" para decriptá-lo */
    getCookie() {
        const cookieEncriptado = Cookies.get('jwt');
        const cookieDecriptado = this.decriptarToken(cookieEncriptado);
        return cookieDecriptado;
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

    /** Função para retirar um cookie salvo, recebendo seu nome como parâmetro */
    limparCookie(nomeCookie) {
        Cookies.remove(nomeCookie);
    }

}

export default new CookieService();