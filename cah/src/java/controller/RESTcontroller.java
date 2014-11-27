package controller;

import database.DBFacade;
import model.*;
import java.util.ArrayList;

public class RESTcontroller {

    private PlayerInformations thisPlayer;       // Player in focus
    private DBFacade dbf;

    public RESTcontroller() {
        thisPlayer = null;
        dbf = DBFacade.getInstance();
    }

    public PlayerInformations getPlayer(String userName) {
        thisPlayer = dbf.getPlayer(userName);
        return thisPlayer;
    }

    public PlayerInformations createNewPlayerAccount(String userName, String password, int pID) {
        //== create order object with pID=0
        thisPlayer = new PlayerInformations(userName, password, 0);

        //== save and get DB-generated unique ono
        boolean status = dbf.saveNewPlayerAccount(thisPlayer);
        if (!status) //fail!
        {
            thisPlayer = null;
        }

        return thisPlayer;
    }

//    public boolean addPlayerDetail(int partNo, int qty) {
//        boolean status = false;
//        if (thisPlayer != null) {
//            PlayerDetail od = new PlayerDetail(thisPlayer.getOno(), partNo, qty);
//            thisPlayer.addDetail(od);
//            status = dbf.saveNewPlayerDetail(od);
//        }
//        return status;
//    }
// **- Objektet er ikke public i klassen PlayerInformation -**
//    public String getPlayerDetailsToString() {
//        if (thisPlayer != null) {
//            return thisPlayer.playerDetailsToString();
//        } else {
//            return null;
//        }
//    }

//    public boolean updatePlayerAccount(int ono, int cno, int eno) {
//        boolean status = dbf.updatePlayerAccount(ono, cno, eno);
//        return status;
//    }
}
