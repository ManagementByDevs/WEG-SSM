//package net.weg.wegssm.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import net.weg.wegssm.model.entities.Usuario;
//import net.weg.wegssm.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.Optional;
//
//@Service
//public class AutenticacaoService implements UserDetailsService {
//
//    private final String senha = "027bd14fb78fd9ca8ef115c6136f1d7b3a810af2c5082c3616b6797467179887"; // senha = weg
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    /**
//     * Método responsável por autenticar o usuário
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
//
//        if (usuarioOptional.isPresent()) {
//            return usuarioOptional.get();
//        }
//
//        throw new UsernameNotFoundException("Dados inválidos!");
//    }
//
//    /**
//     * Gera um token para o usuário informado
//     * @param authentication
//     * @return
//     */
//    public String gerarToken(Authentication authentication) {
//        Usuario usuario = (Usuario) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setIssuer("WEG SSM")
//                .setSubject(usuario.getEmail())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime() + 18000000))
//                .signWith(SignatureAlgorithm.HS256, senha)
//                .compact();
//    }
//
//    /**
//     * Método para validar o token
//     * @param token
//     * @return
//     */
//    public Boolean validarToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(senha).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * Método para obter o email do usuário logado
//     * @param token
//     * @return
//     */
//    public Usuario getUsuario(String token) {
//        String email = Jwts.parser()
//                .setSigningKey(senha)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//
//        return usuarioRepository.findByEmail(email).get();
//    }
//
//}
