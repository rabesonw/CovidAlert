package alert.covid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Class CovidAlertSecurityConfig
 * Configures the security of the application using Spring Security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true ,
        securedEnabled = true ,
        jsr250Enabled = true
)
public class CovidAlertSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**
     * Configures the routes accessible to a user that is not yet connected
     * Handles the connection and opens the routes for the connected user
     * Handles the logout of the user
     * @param httpSecurity configurer
     * @throws Exception
     */
    protected void configure(final HttpSecurity httpSecurity) throws  Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()
                .antMatchers("/doLogin*").permitAll()
                .antMatchers("/register*").permitAll()
                .antMatchers("/locations*").permitAll()
                .antMatchers("/doRegister*").permitAll()
                .antMatchers("/userConfirm").permitAll()
                .antMatchers ( "/userConfirm").permitAll()
                .antMatchers("/static/css/","/static/js/","/images/*").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/createLocation").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .permitAll()
                .defaultSuccessUrl("/",true)
                .and()
                .rememberMe()
                .key("cleSuperSecrete")
                .tokenRepository(tokenRepository())
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .logoutRequestMatcher( new AntPathRequestMatcher("/doLogout","GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.cors();
    }

    /**
     * Configures the authentication of the user
     * @param auth Authentication manager
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource);
    }

    /**
     * Provides a password encoder
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a token
     * @return PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
        return token;
    }

    
}
