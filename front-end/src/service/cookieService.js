import Cookies from 'js-cookie';

class CookieService {

    senhaForte = "05a9e62653eb0eaa116a1b8bbc06dd30ab0df73ab8ae16a500c80875e6e6c8a9";

    getCookie() {
        const cookieEncriptado = Cookies.get('jwt');
        const cookieDecriptado = this.decriptarToken(cookieEncriptado);
        return cookieDecriptado;
    }

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

}

export default new CookieService();