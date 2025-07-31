/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arghy
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TourInsert {
    public static void main(String[] args) {
        // connect to DB
        Connection conn = DBConnectTest.getConnection();
        if (conn == null) {
            System.out.println("❌ Connection failed!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        try {
            // Take input
            System.out.print("Enter Tour Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Destination: ");
            String destination = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            // SQL insert
            String sql = "INSERT INTO tours (tour_name, destination, price) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, destination);
            ps.setDouble(3, price);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Tour added successfully!");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Error inserting data!");
            e.printStackTrace();
        }
    }
}
