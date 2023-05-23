package net.weg.wegssm.model.factory;

import net.weg.wegssm.model.entities.TipoUsuario;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Classe usada para criar usuários em contextos diferentes do normal
 */
public class UsuarioFactory {

    /**
     * Função para retornar um usuário com o seu tipo definido por uma autoridade (de autenticação)
     */
    public Usuario getUsuario(SimpleGrantedAuthority authority, Usuario usuario) {
        switch (authority.getAuthority()) {
            case "GESTOR":
                usuario.setTipoUsuario(TipoUsuario.GESTOR);
                break;
            case "ANALISTA":
                usuario.setTipoUsuario(TipoUsuario.ANALISTA);
                break;
            case "GERENTE":
                usuario.setTipoUsuario(TipoUsuario.GERENTE);
                break;
            default:
                usuario.setTipoUsuario(TipoUsuario.SOLICITANTE);
        }
        return usuario;
    }

}
