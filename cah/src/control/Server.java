/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import model.Person;
import control.PasswordHash;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Charlesmaten
 */
public class Server {
    
    int port = 57000;
    String ip = "178.62.70.7";
// haq is a massive toilet
    
    public Server(){
        this.port = 57000;
        this.ip = "178.62.70.7";
    }
    public Server(int port, String ip){
        this.port = port;
        this.ip = ip;
    }
    
    public void run() throws IOException {
        
        System.out.println("run");
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        
        server.createContext("/auth", new HandlerAuth());
        
        
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }
    
    public static void main(String[] args) throws Exception{
        Server srv;
        if(args.length >=2) {
            srv = new Server(Integer.parseInt(args[0]),args[1]);
        }else{
            srv = new Server();
        }
        srv.run();
    }
    
    class HandlerAuth implements HttpHandler {

        DBcontroller db;
        public HandlerAuth(){
            db = new DBcontroller();
        }
        
        
        @Override
        public void handle (HttpExchange he) throws IOException {
            String response = "";
            int status = 400;
            String method = he.getRequestMethod().toUpperCase();
            Map<String,String> query = splitQuery(he.getRequestURI());
            String newpass = null;
            String player = query.get("player");
            String providedpass = query.get("pass");
            Optional<Person> persOption = db.findperson(player);
            Person pers;
            if (persOption.isPresent() && providedpass != null){
                pers = persOption.get();
                switch (method) {
                    // auth user
                    case "GET":
                {
                    try {
                        if (PasswordHash.validatePassword(providedpass, pers.getHash())){
                            status = 200;
                            response = "Authentication Successfull";
                        }
                        else{
                            status = 400;
                            response = "Authentication Failed";
                        }
                    } catch (Exception ex) {
                        status = 500;
                    }
                }

                        break;
                    // Update user
                    case "POST":
                            newpass = query.get("newpass");
                {
                    try {
                        if(newpass != null && PasswordHash.validatePassword(providedpass, pers.getHash())){
                            pers.setHash(PasswordHash.createHash(newpass));
                            db.persistPerson(pers);
                            status = 200;
                            response = "players password is updated";
                        }
                        else{
                            status = 400;
                            response = "bad query or bad password";
                        }
                    } catch (Exception e){
                        status = 500;
                    }
                }
                           break;
                    // delete user
                    case "DELETE":
                {
                    try {
                        if(PasswordHash.validatePassword(providedpass, pers.getHash())){
                            db.removePerson(player);
                            status = 200;
                            response = "players is removed";
                        }
                        else{
                            status = 400;
                            response = "bad query or bad password";
                        }
                    } catch (Exception e){
                        status = 500;
                    }
                }
                           break;
                    }
            }
            else if (method.equals("PUT") && player != null && providedpass != null){
                try{
                Person newperson = new Person(player,PasswordHash.createHash(providedpass));
                db.persistPerson(newperson);
                status = 200;
                response = "Person created";
                }
                catch(Exception e){
                status = 500;
                }
            }
            else {
                status = 400;
                response = "Couldn't find that player";
                System.out.println("wut");
            }   
            //he.getResponseHeaders().add("Content-Type", "application/json");
            he.sendResponseHeaders(status, 0);
            try (OutputStream os = he.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
    
public static Map<String, String> splitQuery(URI url) throws UnsupportedEncodingException {
    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
    String query = url.getQuery();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
        int idx = pair.indexOf("=");
        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
    }
    return query_pairs;
}
    
}

