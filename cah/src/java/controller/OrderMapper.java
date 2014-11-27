package controller;

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
public class OrderMapper {
    //== load an order and the associated order details

    public PlayerInformations getPlayer(int ono, Connection con) {
        PlayerInformations o = null;
        String SQLString1 = // get order
                "select * "
                + "from orders "
                + "where ono = ?";
        String SQLString2 = // get order details
                "select od.pno, od.qty "
                + "from odetails od "
                + "where od.ono = ? ";         // foreign key match 
        PreparedStatement statement = null;

        try {
            //=== get order
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, ono);     // primary key value
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                o = new PlayerInformations(ono,
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
            }

            //=== get order details
            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, ono);          // foreign key value
            rs = statement.executeQuery();
            while (rs.next()) {
                o.addDetail(new PlayerDetail(
                        ono,
                        rs.getInt(1),
                        rs.getInt(2)));
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - getOrder");
                System.out.println(e.getMessage());
            }
        }
        return o;
    }

    //== Insert new order (tuple)
    public boolean saveNewPlayer(PlayerInformations o, Connection con) {
        int rowsInserted = 0;
        String SQLString1 =
                "select orderseq.nextval  "
                + "from dual";
        String SQLString2 =
                "insert into orders "
                + "values (?,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            //== get unique ono
            statement = con.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                o.setOno(rs.getInt(1));
            }

            //== insert tuple
            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, o.getOno());
            statement.setInt(2, o.getCustomerNo());
            statement.setInt(3, o.getEmployeeNo());
            statement.setString(4, o.getReceived());
            statement.setString(5, o.getShipped());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - saveNewOrder");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - saveNewOrder");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

    //== Insert new order detail (tuple)
    public boolean saveNewPlayerDetail(PlayerDetail od, Connection con) {
        int rowsInserted = 0;
        String SQLString =
                "insert into odetails "
                + "values (?,?,?)";
        PreparedStatement statement = null;

        try {
            //== insert tuple
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, od.getOno());
            statement.setInt(2, od.getPno());
            statement.setInt(3, od.getQty());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - saveNewOrderDetail");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - saveNewOrderDetail");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

    boolean updateOrder(int ono, int cno, int eno, Connection con) {
        int s = 0;
        String update = "UPDATE orders SET cno = ?, eno = ? WHERE ono = ?";
        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setInt(1, cno);
            statement.setInt(2, eno);
            statement.setInt(3, ono);
            s = statement.executeUpdate();
        } catch (SQLException ex) { 
        }
        return s == 1;
    }

}
