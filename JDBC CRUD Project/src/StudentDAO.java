import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // CREATE TABLE
    public void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    age INT NOT NULL,
                    email VARCHAR(100) UNIQUE
                )
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();
            System.out.println("Students table created successfully.");

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // INSERT
    public void insertStudent(Student student) {

        String sql = "INSERT INTO students(name, age, email) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student inserted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }

    // READ
    public void getAllStudents() {

        String sql = "SELECT * FROM students";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- STUDENT RECORDS -----");

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("id")
                                + " | Name: " + rs.getString("name")
                                + " | Age: " + rs.getInt("age")
                                + " | Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Read Error: " + e.getMessage());
        }
    }

    // UPDATE
    public void updateStudent(int id, String newEmail) {

        String sql = "UPDATE students SET email=? WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newEmail);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }
}