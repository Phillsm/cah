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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phill
 */
public class DBcontrollerTest {
    DBcontroller db;

    public DBcontrollerTest() {
        db = new DBcontroller();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findperson method, of class DBcontroller.
     */
    @Test
    public void testFindperson() {
        Person y = new Person("test1","hash1");
        db.persistPerson(y);
        Optional<Person> x = db.findperson("test1");
        assertEquals("hash1",x.get().getHash());
        db.removePerson("test1");
    }

    /**
     * Test of persistPerson method, of class DBcontroller.
     */
   
    
    
}
