package com.garciajuanjo.RegistroUsuariosSpring.repository;


import com.garciajuanjo.RegistroUsuariosSpring.entity.QUserApp;
import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("userAppRepositoryQueryDSL")
public class UserAppRepositoryQueryDSL {

    /*Esta clase la crea QueryDSL y sería como un objeto de nuestra entidad (para este caso UserApp) que luego
    * empleamos en las consultas*/
    private QUserApp qUserApp = QUserApp.userApp;

    /**
     * Este objeto se encarga de la persistencia de los datos en la aplicación y se lo tenemos que pasar a los
     * objetos de JPAQuery que creamos en los métodos para las querys. Tenemos que marcarlo con la anotación
     */
    @PersistenceContext
    private EntityManager em;

    //Comprueba si existe el nombre de usuario en la BBDD
    public boolean existUserAppByUsername(String username) {
        JPAQuery<UserApp> query = new JPAQuery<>(em);

        return query
                .select(qUserApp.username)
                .from(qUserApp)
                .where(qUserApp.username.eq(username))
                .fetchOne() != null;
    }


    //Comprueba si existe el email en la BBDD
    public boolean existEmail(String email) {
        JPAQuery<UserApp> query = new JPAQuery<>(em);

        return query
                .select(qUserApp.email)
                .from(qUserApp)
                .where(qUserApp.email.eq(email))
                .fetchOne() != null;
    }


}
