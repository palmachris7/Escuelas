package palma.app.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, estatus from Usuario where username=?")
                .authoritiesByUsernameQuery("select username as username , perfil as perfil from Usuario_Perfil " +
                        "inner join usuario using (idusuario)" +
                        "inner join perfil using (idperfil) " +
                        "where username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers(
                        "/bootstrap/**",
                        "/images/**")
                .permitAll()

                .antMatchers("/",
                        "/login",
                        "/signup",
                        "/bcrypt/**",
                        "/api/**",
                        "/about",
                        "/escuelas/**")
                .permitAll()

                .antMatchers("/escuelas/create/**").hasAnyAuthority("USUARIO","ADMINISTRADOR")
                .antMatchers("/escuelas/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")

                .anyRequest().authenticated()

                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}