package br.com.zupacademy.marcio.ecommerce.commons.security;

import br.com.zupacademy.marcio.ecommerce.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService AutenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    // Configurações de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(AutenticacaoService).passwordEncoder(new BCryptPasswordEncoder());

    }
    // Configurações de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/categorias").permitAll()
                .antMatchers(HttpMethod.GET,"/categorias/*").permitAll()
                .antMatchers(HttpMethod.GET,"/produtos/*").permitAll()
                .antMatchers(HttpMethod.POST,"/categorias").authenticated()
                .antMatchers(HttpMethod.POST,"/produtos").authenticated()
                .antMatchers(HttpMethod.POST,"/produtos/imagens/*").authenticated()
                .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
                .antMatchers(HttpMethod.POST,"/produtos/opinioes/*").authenticated()
                .antMatchers(HttpMethod.POST,"/produtos/perguntas/*").authenticated()
                .antMatchers(HttpMethod.POST,"/compras").authenticated()
                .antMatchers(HttpMethod.POST,"/pagamentos/*").authenticated()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
//                .antMatchers(HttpMethod.GET,"/actuator/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

    }

    // Configurações de recursos estáticos (js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
