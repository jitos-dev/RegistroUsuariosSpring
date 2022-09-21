package com.garciajuanjo.RegistroUsuariosSpring.impl;

import com.garciajuanjo.RegistroUsuariosSpring.entity.Roles;
import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userAppService")
public class UserAppService implements UserDetailsService {

    /*Inyectamos el service UserAppService (pongo el paquete porque se llama como esta clase) para las
    * operaciones con BBDD*/
    @Autowired
    @Qualifier("userAppServiceImpl")
    private com.garciajuanjo.RegistroUsuariosSpring.service.UserAppService userAppService;

    /**
     * Este método se encarga de transformar un UserApp (de nuestra entidad) a un UserDetails (de Spring). Para esto
     * buscamos el UserApp (de nuestra entidad) con el repositorio mediante el username. Luego creamos un
     * List de GrantedAuthority y utilizamos el método buildAuthorities que tenemos abajo para obtener
     * el List mapeado. Con el UserApp y el list utilizamos el método buildUser que devuelve un objeto
     * de User (de spring security) que podemos utilizar como UserDetails.
     * @param username Username del formulario de login
     * @return buildUser(UserApp, List de GrantedAuthority)
     * @throws UsernameNotFoundException si no encuentra al usuario para que nos quite los errores
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Obtenemos el usuario por el username
        UserApp userApp = userAppService.findByUsername(username);

        //List de GrantedAuthority para los roles del usuario
        List<GrantedAuthority> authorities;

        /*Si el usuario no es null parseamos la lista de Roles a la de GrantedAutority y devolvemos el UserDetails,
         * si es null lanzamos la excepción*/
        if (userApp != null)
            authorities = buildAutorities(userApp.getRoles());
        else
            throw new UsernameNotFoundException("Usuario no encontrado");

        return buildUser(userApp, authorities);
    }


    /**
     * Este método se encarga de transformar la lista de Roles (es un set) de la entidad UserApp a una
     * List de objetos GrantedAuthority. Para esto mapeamos los objetos de la entidad Roles a un new SimpleGrantedAuthority
     * donde le pasamos por parámetro el role (la clase Roles tiene más parámetros)
     * @param roles set de Roles del objeto de la entidad UserApp
     * @return List de objetos GrantedAuthority
     */
    private List<GrantedAuthority> buildAutorities(Set<Roles> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


    /**
     * Este método se encarga de transformar un objeto UserApp de nuestra entidad a un objeto User de spring security
     * por eso en el que se pasa por parámetro se pone el nombre del paquete para que los distinga. La list de
     * GrantedAuthority sería una lista de nuestra entidad Roles.
     * Los parámetros del constructor de User (User de spring security) son:
     *  - username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked y authorities
     * @param userApp objeto de la entidad UserApp
     * @param authorities lista de GrantedAuthority que serían de nuestra entidad Roles pero transformados a el
     *                    objeto GrantedAuthority
     * @return objeto User de org.springframework.security.core.userdetails.User
     */
    private User buildUser(UserApp userApp, List<GrantedAuthority> authorities) {
        return new User(userApp.getUsername(), userApp.getPassword(), userApp.isEnabled(),
                true, true, true, authorities);
    }


}
