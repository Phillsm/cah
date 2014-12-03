/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
/**
 *
 * @author Phill
 */
public class Server {

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cahPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Person findPerson(String name){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cahPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person x = em.find(Person.class,name);
        return x;
    }
    
    public static void main(String[] args) {
        Person test = new Person("testperson","testhash");
        Server serv = new Server();
        
        serv.persist(test);
        
        Person x = serv.findPerson("testperson");
        
        System.out.println(x.getHash());
        System.out.println("ok?");
        
        
    }


}
