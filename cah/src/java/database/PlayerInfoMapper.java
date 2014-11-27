package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PlayerInformations;
import model.PlayerDetail;
import java.util.logging.Level;
import java.util.logging.Logger;

//=== Maps between objects and tables
//=== Encapsulates SQL-statements
// hau
public class PlayerInfoMapper {
    //== load a player and the associated player details

    public PlayerInformations getPlayer(String userName, Connection con) {
        PlayerInformations playInfo = null;
        String SQLString1 = // get player
                "select * "
                + "from username "
                + "where userName = ?";
        String SQLString2 = // get player details
                "select od.password, od.playerID "
                + "from odetails od "
                + "where od.userName = ? ";         // foreign key match 
        PreparedStatement statement = null;

        try {
            //=== get player
            statement = con.prepareStatement(SQLString1);
            statement.setString(1, userName);     // primary key value
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                playInfo = new PlayerInformations(userName,
                        rs.getString(2),
                        rs.getInt(3));
                        
            }

            //=== get player details
            statement = con.prepareStatement(SQLString2);
            statement.setString(1, userName);          // foreign key value
            rs = statement.executeQuery();
            while (rs.next()) {
                playInfo.addDetail(new PlayerDetail(
                        userName,
                        rs.getString(1),
                        rs.getInt(2)));
            }
        } catch (Exception e) {
            System.out.println("Fail in PlayerInfoMapper - getPlayer");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in PlayerInfoMapper - getPlayer");
                System.out.println(e.getMessage());
            }
        }
        return playInfo;
    }

    //== Insert new order (tuple)
    public boolean saveNewPlayer(PlayerInformations o, Connection con) {
        int rowsInserted = 0;
        String SQLString1 =
                "select orderseq.nextval  "
                + "from dual";
        String SQLString2 =
                "insert into players "
                + "values (?,?,?)";
        PreparedStatement statement = null;

        try {
            //== get unique userName
            statement = con.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                o.setUserName(rs.getString(1));
            }

            //== insert tuple
            statement = con.prepareStatement(SQLString2);
            statement.setString(1, o.getUserName());
            statement.setString(2, o.getPassword());
            statement.setInt(3, o.getPlayerID());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in PlayerInfoMapper - saveNewPlayer");
            System.out.println(e.getMessage());
        } finally // close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in PlayerInfoMapper - saveNewPlayer");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

//    //== Insert new player detail (tuple)
//    public boolean saveNewPlayerDetail(PlayerDetail od, Connection con) {
//        int rowsInserted = 0;
//        String SQLString =
//                "insert into odetails " //Skal lige fixes så info kommer i de respektive tabeller
//                + "values (?,?,?)";
//        PreparedStatement statement = null;
//
//        try {
//            //== insert tuple
//            statement = con.prepareStatement(SQLString);
//            statement.setString(1, od.getUserName());
//            statement.setString(2, od.getPassword());
//            statement.setInt(3, od.getPlayerID());
//            rowsInserted = statement.executeUpdate();
//        } catch (Exception e) {
//            System.out.println("Fail in PlayerInfoMapper - saveNewPlayerDetail");
//            System.out.println(e.getMessage());
//        } finally // must close statement
//        {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                System.out.println("Fail in PlayerInfoMapper - saveNewPlayerDetail");
//                System.out.println(e.getMessage());
//            }
//        }
//        return rowsInserted == 1;
//    }
//
//    boolean updateOrder(String userName, String password, int pID, Connection con) {
//        int s = 0;
//        String update = "UPDATE players SET password = ?, pID = ? WHERE userName = ?";
//        try {
//            PreparedStatement statement = con.prepareStatement(update);
//            statement.setString(1, userName);
//            statement.setString(2, password);
//            statement.setInt(3, pID);
//            s = statement.executeUpdate();
//        } catch (SQLException ex) { 
//        }
//        return s == 1;
//    }
}
