package net.weg.wegssm.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.wegssm.model.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe usada como intermediadora entre a classe "UserDetails" e "Usuario" para realização da autenticação
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = UserJpaDeserializer.class)
public class UserJpa implements UserDetails {

    private Usuario usuario;

    private Collection<GrantedAuthority> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @JsonIgnore
    private String password;

    private String username;

    public UserJpa(Usuario usuario){
        this.usuario = usuario;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.password = usuario.getSenha();
        this.username = usuario.getEmail();
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority(usuario.getTipoUsuario().toString()));
    }
}
