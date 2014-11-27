package model;

/**
 *
 * @author Fisk
 */
public class Player {

    public Player(String userName, String password, String playerID) {
        this.userName = userName;
        this.password = password;
        this.playerID = playerID;
    }
    
    private String userName;
    private String password;
    private String playerID;

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

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }    
}
