package database;

import database.PlayerInfoMapper;
import database.DBConnector;
import java.sql.Connection;
import model.*;

// Encapsulates the Data Source Layer
// Encapsulates connection handling 
// Implemented as a Singleton to provide global access from
// Domain classes and to ensure the use of max one connection
// hau
public class DBFacade {

	  private PlayerInfoMapper om; 
	  private Connection con;
	  
	  //== Singleton start
	  private static DBFacade instance;
	 
	  private DBFacade() {
		  om 	= new PlayerInfoMapper();
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

	  
	  public PlayerInformations getPlayer(String userName) 
	  {
		  return om.getPlayer(userName, con);	      
	  }
	  
	  public boolean saveNewPlayerAccount(PlayerInformations o)
	  { 
	    return om.saveNewPlayer(o, con);
	  }
	  
//	  public boolean saveNewPlayerDetail(PlayerDetail pDetail)
//	  {
//	    return om.saveNewPlayerDetail(pDetail, con);
//	  }
//
//    public boolean updatePlayerAccount(String userName, String password, int pID) {
//        return om.updateOrder(userName, password, pID, con);
//    }	
}
