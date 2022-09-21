package com.garciajuanjo.RegistroUsuariosSpring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration //Para indicar que es una clase de configuracion
@EnableWebSecurity //Para habilitar la seguridad web
@EnableGlobalMethodSecurity(prePostEnabled = true) //para controlar el acceso a los métodos y darle seguridad
public class SecurityConfiguration {

    @Autowired
    @Qualifier("userAppService")
    private UserDetailsService userAppService;

    /**
     * Este método
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAppService).passwordEncoder(passwordEncoder());
    }

    /**
     * Este método es el que se encarga de las configuraciones de seguridad del login y logout de
     * la web. Al utilizar spring security tiene varias configuraciones ya prestablecidas que
     * nos ayudan en esta parte.
     * antMatchers() que es para indicar las request que estan autorizadas sin necesidad
     * de ningún login (los css, imagenes, js, etc). /css/* significa que esta en resources/static css y tod
     * lo que contenga dentro la carpeta css y permitAll() que es que lo permitimos a todos. Aparte también permitimos
     * que puedan entrar en el la raiz (/), en el login y en el formulario de registro
     * anyRequest().authenticated() es que el resto de request necesitan autentificación (las vistas)
     * and() es como para concatenar (y)
     * formLogin()
     *      loginPage(/login) dentro de resources/templates cual es la vista del login
     *      loginProcessingUrl(/loginurl) es el action que tenemos que ponerle al form del login. Puede
     *      ser el que queramos y no es ningún método solo tiene que coincidir el texto
     *     usernameParameter(username) el nombre del atributo de la entidad UserApp que es el nombre de usuario. En
     *     este caso es un username pero podríamos utilizar por ejemplo un email, dni, etc.
     *     passwordParameter(password) el nombre del atributo de la entidad UserApp para la contraseña
     *     defaultSuccessUrl(/loginsuccess) el nombre del método del controlador que redirige a la vista una vez
     *     que el login es correcto.
     *     logout().logoutUrl(/logout) este es para el logout y tiene que llamarse exactamente así. Logout no es
     *     un método nuestro (es de spring security)
     *     logoutSuccessUrl(/login?logout) método del controlador que devuelve a la vista del login. Le pasamos el
     *     ?logout porque ese método recoge un parámetro por get (logout) para que muestre un mensaje de correcto
     *     cuando el logout es correcto
     *     permiteAll() para permitir todos estos ajustes.
     *     Por ultimo el return tiene que quedar exactamente igual
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/css/*", "/imgs/*", "/js/*").permitAll()
                    .antMatchers(allowedPages()).permitAll()
                    //.antMatchers("/admin/panel").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
                    //.antMatchers("/users/panel").access("hasRole('ROLE_USER')")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginurl")
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/login/loginsuccess").permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                .permitAll();
        return http.build();
    }

    /**
     * Este es un @Bean de Spring que se encarga del cifrado de las contraseñas. El número del parámetro es como de
     * fuerte quieres que sea la contraseña (valores entre 4 y 31). Si no se pone nada el programa cada vez
     * utiliza uno aleatorio y puede dar problemas
     * @return new BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * Método para poner las páginas que estan permitidas para todos los usuarios de la aplicación y que así en
     * el método de arriba (filterChain) quede más fino
     * @return array de String con las páginas permitidas
     */
    private String[] allowedPages() {
        return new String[]{"/", "/login", "/register/formRegister", "/register/addUser"};
    }
}
