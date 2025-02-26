package USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Userdaoimp implements UserDaointer {

    Connection con = DBConnection.createDBConnection();

    @Override
    public void createUser(User user) {
        String query = "INSERT INTO user_profiles (id, name, phone, address) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, user.getId());
            pstm.setString(2, user.getName());
            pstm.setString(3, user.getPhone());
            pstm.setString(4, user.getAddress());
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                System.out.println("User profile created successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProfile(int id) {
        String query = "SELECT * FROM user_profiles WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println("User Details:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("address"));
            } else {
                System.out.println("No user found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int id, String name, String phone, String address) {
        String query = "UPDATE user_profiles SET name = ?, phone = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, phone);
            pstm.setString(3, address);


            pstm.setInt(4, id);
            int cnt = pstm.executeUpdate();

            if (cnt > 0) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Profile update failed. User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM user_profiles WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            int cnt = pstm.executeUpdate();
            if (cnt > 0) {
                System.out.println("Your profile deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

