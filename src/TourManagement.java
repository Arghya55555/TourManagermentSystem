import java.sql.*;
import java.util.Scanner;

public class TourManagement {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== TOUR MANAGEMENT SYSTEM =====");
            System.out.println("1. Manage Tours");
            System.out.println("2. Manage Customers");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    manageTours();
                    break;
                case 2:
                    manageCustomers();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // -------------------- TOURS MENU --------------------
    public static void manageTours() {
        while (true) {
            System.out.println("\n--- Manage Tours ---");
            System.out.println("1. Add Tour");
            System.out.println("2. View Tours");
            System.out.println("3. Update Tour Price");
            System.out.println("4. Delete Tour");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addTour(); break;
                case 2: viewTours(); break;
                case 3: updateTour(); break;
                case 4: deleteTour(); break;
                case 5: return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // -------------------- CUSTOMERS MENU --------------------
    public static void manageCustomers() {
        while (true) {
            System.out.println("\n--- Manage Customers ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer (No. of People)");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addCustomer(); break;
                case 2: viewCustomers(); break;
                case 3: updateCustomer(); break;
                case 4: deleteCustomer(); break;
                case 5: return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // -------------------- TOURS CRUD --------------------
    public static void addTour() {
        try (Connection conn = DBConnectTest.getConnection()) {
            System.out.print("Enter Tour Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Destination: ");
            String dest = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            String sql = "INSERT INTO tours (tour_name, destination, price) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dest);
            ps.setDouble(3, price);

            if (ps.executeUpdate() > 0) System.out.println("✅ Tour added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewTours() {
        try (Connection conn = DBConnectTest.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tours");
            System.out.println("\nID\tName\tDestination\tPrice");
            while (rs.next()) {
                System.out.println(rs.getInt("tour_id") + "\t" +
                                   rs.getString("tour_name") + "\t" +
                                   rs.getString("destination") + "\t" +
                                   rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTour() {
        try (Connection conn = DBConnectTest.getConnection()) {
            System.out.print("Enter Tour ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            String sql = "UPDATE tours SET price=? WHERE tour_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, id);

            if (ps.executeUpdate() > 0) System.out.println("✅ Tour updated!");
            else System.out.println("⚠️ Tour ID not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteTour() {
        try (Connection conn = DBConnectTest.getConnection()) {
            System.out.print("Enter Tour ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM tours WHERE tour_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) System.out.println("✅ Tour deleted!");
            else System.out.println("⚠️ Tour ID not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------- CUSTOMERS CRUD --------------------
    public static void addCustomer() {
        try (Connection conn = DBConnectTest.getConnection()) {
            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Tour ID (must exist): ");
            int tourId = sc.nextInt();
            System.out.print("Enter Number of People: ");
            int numPeople = sc.nextInt();
            sc.nextLine();

            String sql = "INSERT INTO customers (cust_name, tour_id, num_people) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, tourId);
            ps.setInt(3, numPeople);

            if (ps.executeUpdate() > 0) System.out.println("✅ Customer added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewCustomers() {
        try (Connection conn = DBConnectTest.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customers");
            System.out.println("\nID\tName\tTour ID\tNo. of People");
            while (rs.next()) {
                System.out.println(rs.getInt("cust_id") + "\t" +
                                   rs.getString("cust_name") + "\t" +
                                   rs.getInt("tour_id") + "\t" +
                                   rs.getInt("num_people"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer() {
        try (Connection conn = DBConnectTest.getConnection()) {
            System.out.print("Enter Customer ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new Number of People: ");
            int numPeople = sc.nextInt();
            sc.nextLine();

            String sql = "UPDATE customers SET num_people=? WHERE cust_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, numPeople);
            ps.setInt(2, id);

            if (ps.executeUpdate() > 0) System.out.println("✅ Customer updated!");
            else System.out.println("⚠️ Customer ID not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer() {
        try (Connection conn = DBConnectTest.getConnection()) {
            System.out.print("Enter Customer ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM customers WHERE cust_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) System.out.println("✅ Customer deleted!");
            else System.out.println("⚠️ Customer ID not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
