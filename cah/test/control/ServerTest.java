/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import static javafx.css.StyleOrigin.USER_AGENT;
import org.eclipse.persistence.internal.jpa.querydef.TupleImpl;
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
public class ServerTest {
    static Server srv = null;
    
    public ServerTest() throws IOException{
        if (srv == null){
        srv = new Server(4000,"127.0.0.1");
        srv.run();
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       // sendMethod("PUT")
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGET() throws Exception{
        String person = "getperson";
        String hash = "gethash";
        
        //insert person
        assertEquals(200,sendMethod("PUT",person,hash,null));
        
        //get person
        assertEquals(200,sendMethod("GET",person,hash,null));
        //Can't get nonexisting person
        assertEquals(400,sendMethod("GET","idontexist",hash,null));
        //can't authenticate with wrong hash
        assertEquals(400,sendMethod("GET",person,"wronghash",null));
        
        //cleanup
        assertEquals(200,sendMethod("DELETE",person,hash,null));
       
    }
    @Test
    public void testPOST() throws Exception{
        String person = "person1";
        String hash = "hash1";
        
        //insert person
        assertEquals(200,sendMethod("PUT",person,hash,null));
        
        // update password
        assertEquals(200,sendMethod("POST",person,hash,"newhash"));
        
        //can't update hash with bad oldhash
        assertEquals(400,sendMethod("POST",person,"wronghash","icantdothis"));
        
        //confirm that I can get person using new hash
        assertEquals(200,sendMethod("GET",person,"newhash",null));
        
        //cleanup   
        assertEquals(200,sendMethod("DELETE",person,"newhash",null));

        
    }
    
    @Test
    public void testDelete() throws Exception{
            String person = "deleteperson";
        String hash = "deletehash";
        
        //insert person
        assertEquals(200,sendMethod("PUT",person,hash,null));
       
        //try to delete with wrong password
        assertEquals(400,sendMethod("DELETE",person,"newhash",null));
        
        //confirm that person is still there
        assertEquals(200,sendMethod("GET",person,hash,null));
        
        //delete using right hash
        assertEquals(200, sendMethod("DELETE",person,hash,null));
        
        //confirm that user is missing
        assertEquals(400,sendMethod("GET",person,hash,null));

    
    }
    
    private int sendMethod(String method, String name, String hash, String newhash) throws Exception {
                
		String url = "http://127.0.0.1:4000/auth?player="+name+"&hash="+hash;
                if (newhash != null){url += "&newhash="+newhash;}
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod(method);
 
		//add request header
		con.setRequestProperty("User-Agent","USER_AGENT");
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending "+method+" request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
                if (responseCode >= 199 && responseCode < 300){
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                		System.out.println(response.toString());

                }
                
                return responseCode;
 
	}
   

//    /**
//     * Test of run method, of class Server.
//     */
//    @Test
//    public void testRun() throws Exception {
//        System.out.println("run");
//        Server instance = new Server();
//        instance.run();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of main method, of class Server.
//     */
//    @Test
//    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] args = null;
//        Server.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of splitQuery method, of class Server.
//     */
//    @Test
//    public void testSplitQuery() throws Exception {
//        System.out.println("splitQuery");
//        URI url = null;
//        Map<String, String> expResult = null;
//        Map<String, String> result = Server.splitQuery(url);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
