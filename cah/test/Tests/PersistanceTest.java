/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
import org.junit.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Phill
 */
public class PersistanceTest {
    
    EntityManager em;
    Person[] Testpersons;
   
    @Before
    public void before(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cahPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Testpersons = new Person[3];
        Testpersons[0] = new Person("p1","h1");
        Testpersons[1] = new Person("p2","h2");
        Testpersons[2] = new Person("p3","h3");

    }
    
    @After
    public void after(){
        //database cleanup
        em.getTransaction().rollback();
    }
    
    
    @Test
    public void PersistanceUnitCanSaveAndFetch(){
        //persist
        for(Person x : Testpersons){
            em.persist(x);
        }
        //test that I can pull data back up
        Person x = em.find(Person.class, "p2");
        Assert.assertEquals("h2",x.getHash());
        
    }
    
    @Test
    public void AUsersHashCanBeUpdates(){
        for(Person x : Testpersons){
            em.persist(x);
        }
        Person x = em.find(Person.class,"p1");
        //check that person is fetched right
        Assert.assertEquals("h1",x.getHash());
        // update his hash
        x.setHash("newhash");
        // save again
        em.persist(x);
        
        //find again
        Person y = em.find(Person.class,"p1");
        // check that hash is updated
        Assert.assertEquals("newhash",y.getHash());
        
               
    }
}
