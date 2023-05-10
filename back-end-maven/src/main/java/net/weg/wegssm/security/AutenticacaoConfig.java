package net.weg.wegssm.security;

import lombok.AllArgsConstructor;
import net.weg.wegssm.security.util.CookieUtils;
import net.weg.wegssm.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Classe de configuração geral para a autenticação do sistema
 */
@Configuration
@AllArgsConstructor
public class AutenticacaoConfig {

    /**
     * Serviço usado como base para as autenticações
     */
    private JpaService jpaService;

    /**
     * Configurações do serviço utilizado para autenticação e encriptação de senhas
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jpaService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Configurações das rotas que serão acessadas com e sem autenticação, assim como opções adicionais
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/weg_ssm/login", "/weg_ssm/login/auth", "/weg_ssm/usuario/email").permitAll()

                .antMatchers(HttpMethod.POST, "/weg_ssm/proposta").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.POST, "/weg_ssm/proposta/sem-arquivos").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.PUT, "/weg_ssm/proposta/{id}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.PUT, "/weg_ssm/proposta/add-historico/{idProposta}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.PUT, "/weg_ssm/proposta/update-novos-dados/{idProposta}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.GET, "/weg_ssm/proposta").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.GET, "/weg_ssm/proposta/page").hasAnyAuthority("ANALISTA", "GESTOR")

                .antMatchers(HttpMethod.POST, "/weg_ssm/tabela-custo").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.PUT, "/weg_ssm/tabela-custo").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/weg_ssm/tabela-custo/{id}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.POST, "/weg_ssm/cc").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/weg_ssm/cc/{id}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.POST, "/weg_ssm/custo").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/weg_ssm/custo/{id}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.POST, "/weg_ssm/responsavel_negocio").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.PUT, "/weg_ssm/responsavel_negocio").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/weg_ssm/responsavel_negocio/{id}").hasAnyAuthority("ANALISTA", "GESTOR")

                .antMatchers(HttpMethod.GET, "/weg_ssm/escopo-proposta/demanda/{idDemanda}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.POST, "/weg_ssm/escopo-proposta").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.PUT, "/weg_ssm/escopo-proposta").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/weg_ssm/escopo-proposta/{id}").hasAnyAuthority("ANALISTA", "GESTOR")

                .antMatchers(HttpMethod.GET, "/weg_ssm/pdf/proposta/{id}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.GET, "/weg_ssm/pdf/pauta/{id}").hasAnyAuthority("ANALISTA", "GESTOR")
                .antMatchers(HttpMethod.GET, "/weg_ssm/pdf/ata/{id}").hasAnyAuthority("ANALISTA", "GESTOR")

                .antMatchers(HttpMethod.PUT, "/weg_ssm/chat/{id}").hasAnyAuthority("ANALISTA", "GESTOR")

                .antMatchers(HttpMethod.POST, "/weg_ssm/mensagem").hasAnyAuthority("ANALISTA", "GESTOR")

                .anyRequest().authenticated();

        httpSecurity.csrf().disable();
        httpSecurity.cors().configurationSource(corsConfigurationSource());
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(new AutenticacaoFiltro(new CookieUtils(), new TokenUtils(), jpaService), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    /**
     * Função para retornar as configurações do CORS
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:3000"
        ));
        corsConfiguration.setAllowedMethods(List.of(
                "POST", "DELETE", "GET", "PUT"
        ));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    /**
     * Função usada para retornar um "AuthenticationManager" quando for preciso
     */
    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration ac) throws Exception {
        return ac.getAuthenticationManager();
    }

}
