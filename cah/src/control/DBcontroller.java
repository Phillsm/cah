/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;

/**
 *
 * @author Phill
 */
public class DBcontroller {
    
    private EntityManager em;

    public DBcontroller() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cahPU");
        em = emf.createEntityManager();
    }
    
    public Optional<Person> findperson(String name){
        try{
           Person x = em.find(Person.class, name);
           return Optional.of(x);
        }
        catch(Exception e){
           return Optional.empty();
        }
    }
    
    public void persistPerson(Person p){
       em.getTransaction().begin();
       try{
       em.persist(p);
          em.getTransaction().commit();
       }
       catch(Exception e){
           em.getTransaction().rollback();
       }

    }
    
    public void removePerson(String name){
        em.getTransaction().begin();
        try{
            Person x = em.find(Person.class, name);
            em.remove(x);
            em.getTransaction().commit();
        }
        catch(Exception e){
            em.getTransaction().rollback();
        }
    }
    
    
    
    
}
