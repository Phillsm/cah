package model;

import java.util.ArrayList;

public class PlayerInformations
{

	  private String userName; 
	  private String password; // A Foreign Key should be be mapped to a reference
	  private int playerID; // A Foreign Key should be mapped to a reference
	  private ArrayList<PlayerDetail> playerDetails;
	
	  public PlayerInformations(String name, String pWord, int pID)
	  {
	    userName = name;
	    password = pWord;
	    playerID = pID;
	    //playerDetails = new ArrayList<PlayerDetail>();
	  }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public ArrayList<PlayerDetail> getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(ArrayList<PlayerDetail> playerDetails) {
        this.playerDetails = playerDetails;
    }
    
	  public void addDetail(PlayerDetail pDetail)
	  {
	    playerDetails.add(pDetail);
	  }
          
	  public String toString()
	  {
	    return userName + " " + password + " " + playerID;
	  }
	
	  String playerDetailsToString()
	  {
	    String res = "";
	    for (int i = 0; i < playerDetails.size(); i++) 
	    {
	      res += playerDetails.get(i).toString() + "\n";
	    }
	    return res;
	  }
}
