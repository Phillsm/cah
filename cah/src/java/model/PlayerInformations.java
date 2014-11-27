package model;

import java.util.ArrayList;
//=== hau
public class PlayerInformations
{

	  private int ono; 
	  private int cno; // A FK that should be mapped to a reference
	  private int eno; // A FK that should be mapped to a reference
	  private String received;
	  private String shipped;
	  private ArrayList<PlayerDetail> playerDetails;
	
	  public PlayerInformations(int o, int c, int e, String r, String s)
	  {
	    ono = o;
	    cno = c;
	    eno = e;
	    received = r;
	    shipped = s;
	    playerDetails = new ArrayList<PlayerDetail>();
	  }
	  
	  //== accessors
	  public int getOno()
	  {
	    return ono;
	  }
	  public void setOno(int ono)
	  {
	    this.ono = ono;
	  }
	
	  public void setCustomer(int c)
	  {
	    this.cno = c;
	  }
	
	  public int getCustomerNo()
	  {
	    return cno;
	  }
	
	  public void setEmployee(int e)
	  {
	    this.eno = e;
	  }
	
	  public int getEmployeeNo()
	  {
	    return eno;
	  }
	
	  public void setReceived(String received)
	  {
	    this.received = received;
	  }
	
	  public String getReceived()
	  {
	    return received;
	  }
	
	  public void setShipped(String shipped)
	  {
	    this.shipped = shipped;
	  }
	
	  public String getShipped()
	  {
	    return shipped;
	  }
	  public void addDetail(PlayerDetail od)
	  {
	    playerDetails.add(od);
	  }
	  public String toString()
	  {
	    return ono + " " + cno + " " + eno + " " + received + " " + shipped;
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
