//package net.weg.wegssm.security;
//
//import lombok.Data;
//import net.weg.wegssm.model.entities.Usuario;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Classe usada como intermediadora entre a classe "UserDetails" e "Usuario" para realização da autenticação
// */
//@Data
//public class UserJpa implements UserDetails {
//
//    private Usuario usuario;
//
//    private Collection<GrantedAuthority> authorities;
//
//    private boolean accountNonExpired = true;
//
//    private boolean accountNonLocked = true;
//
//    private boolean credentialsNonExpired = true;
//
//    public UserJpa(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    @Override
//    public String getPassword() {
//        return usuario.getSenha();
//    }
//
//    @Override
//    public String getUsername() {
//        return usuario.getEmail();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return usuario.getVisibilidade();
//    }
//
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(this.getUsuario().getTipoUsuario().toString()));
//
//        return authorities;
//    }
//}
