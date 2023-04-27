package net.weg.wegssm.factory;

import net.weg.wegssm.model.entities.TipoUsuario;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsuarioFactory {

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
