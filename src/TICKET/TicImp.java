package TICKET;

import USER.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class TicImp implements TicInter {
    Connection con = DBConnection.createDBConnection();




    @Override
    public void buyTicket(int userId, String departure, String destination) {
        String query = "INSERT INTO tickets(userId, departure, destination, doj, price) VALUES (?, ?, ?, NOW(), ?)";
        try {
            // Generate a random price between 600 and 1500
            Random random = new Random();
            double price = 600 + (random.nextInt(901)); // Generates a number between 600 and 1500

            PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, userId);
            pstm.setString(2, departure);
            pstm.setString(3, destination);
            pstm.setDouble(4, price); // Pass the generated price

            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                // Retrieve the generated ticket ID
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    int ticketId = rs.getInt(1);
                    System.out.println("Ticket purchased successfully! Your Ticket ID: " + ticketId);
                    System.out.println("Price: " + price);
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

    @Override
    public void showTicket(int ticketId) {
        try {
            String query = "SELECT t.ticketId, u.name, u.phone, t.departure, t.destination, t.doj, t.price " +
                    "FROM tickets t INNER JOIN user_profiles u ON t.userId = u.id WHERE t.ticketId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("----------------------------------------------");
                System.out.println("*-*-*-*WELCOME TO ONLINE BUS TICKETS*-*-*-*");
                System.out.println("----------------------------------------------");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("-------  --------  --------  --------  -------");
                System.out.println("TicketId: " + rs.getInt("ticketId"));
                System.out.println(rs.getString("departure") + " -> " + rs.getString("destination"));
                System.out.println("DOJ: " + rs.getTimestamp("doj"));
                System.out.println("TK " + rs.getDouble("price"));
                System.out.println("-------  --------  --------  --------  -------");
                System.out.println("Thanks from onlinebustickets.com");
                System.out.println("----------------------------------------------");
                System.out.println("----------------------------------------------");

            } else {
                System.out.println("Ticket not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
