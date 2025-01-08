package TICKET;

import USER.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TicImp implements TicInter {

    Connection con = DBConnection.createDBConnection();

    @Override
    public void buyTicket(Ticket ticket) {
        String query = "INSERT INTO tickets(userId, departure, destination) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, ticket.getUserId());
            pstm.setString(2, ticket.getDeparture());
            pstm.setString(3, ticket.getDestination());
            int cnt = pstm.executeUpdate();

            if (cnt > 0) {
                // Retrieve the generated ticket ID
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    int ticketId = rs.getInt(1);
                    ticket.setTicketId(ticketId);
                    System.out.println("Your Ticket purchased successfully! Ticket ID: " + ticketId);
                }
            } else {
                System.out.println("Ticket purchase failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelTicket(int userId, int ticketId) {
        String query = "DELETE FROM tickets WHERE userId = ? AND ticketId = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, userId);
            pstm.setInt(2, ticketId);
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                System.out.println("Ticket canceled successfully!");
            } else {
                System.out.println("Ticket not found or already canceled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
