package controller;

import java.sql.Connection;
import model.*;

// Encapsulates the Data Source Layer
// Encapsulates connection handling 
// Implemented as a Singleton to provide global access from
// Domain classes and to ensure the use of max one connection
// hau
public class DBFacade {

	  private OrderMapper om; 
	  private Connection con;
	  
	  //== Singleton start
	  private static DBFacade instance;
	 
	  private DBFacade() {
		  om 	= new OrderMapper();
		  con 	= new DBConnector().getConnection();  // the connection will be released upon program 
		  					      // termination by the garbage collector		  
	  }
	  public static DBFacade getInstance()
	  {
		  if(instance == null)
			  instance = new DBFacade();
		  return instance;
	  }
	  //== Singleton end

	  
	  public PlayerInformations getPlayer(int ono) 
	  {
		  return om.getPlayer(ono, con);	      
	  }
	  
	  public boolean saveNewPlayerAccount(PlayerInformations o)
	  { 
	    return om.saveNewPlayer(o, con);
	  }
	  
	  public boolean saveNewPlayerDetail(PlayerDetail od)
	  {
	    return om.saveNewPlayerDetail(od, con);
	  }

    public boolean updatePlayerAccount(int ono, int cno, int eno) {
        return om.updateOrder(ono, cno, eno, con);
    }	
}
