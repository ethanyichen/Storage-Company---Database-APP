package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.ServerErrorException;
import ca.ubc.cs304.model.Membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberController extends Controller {
    private Connection connection;

    public MemberController(DatabaseConnectionHandler db) throws ServerErrorException {
        super(db);
        connection = super.connection;
    }


    public void addMember(Membership m) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Member VALUES (?,?,?)");
            ps.setInt(1, m.getWarehouseID());
            ps.setInt(2, m.getCustomerID());
            ps.setString(3, m.getMembershipStartDate());
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
    }
}
