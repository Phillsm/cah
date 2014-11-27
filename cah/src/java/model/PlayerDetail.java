package model;

public class PlayerDetail {
	  private String userName; // userName and password are Foreign Key's but also a constitute Primary Key!  
	  private String password; // Therefore they are kept in the object (Identity Map)
	  private int playerID;
	  public PlayerDetail(String name, String pass, int pID)
	  {
	    userName = name;
	    password = pass;
	    playerID = pID;
	  }

            public String getUserName() {
                return userName;
            }

            public String getPassword() {
                return password;
            }

            public int getPlayerID() {
                return playerID;
            }
	
	  
	  public String toString()
	  {
	    return userName + " " + password + " " + playerID;
	  }

}
