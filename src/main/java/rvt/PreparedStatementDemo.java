package main.java.rvt;
import java.sql.*;

public class PreparedStatementDemo {

    private static final String DB_URL = "jdbc:sqlite:demo.db";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);

        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS todo (id INTEGER PRIMARY KEY, title TEXT NOT NULL, done INTEGER DEFAULT 0)"
        );

        // ievietošana ar PreparedStatement (novērš SQL injection)
        PreparedStatement ps = conn.prepareStatement("INSERT INTO todo(title, done) VALUES(?, ?)");
        ps.setString(1, "Buy milk");
        ps.setInt(2, 0);
        ps.executeUpdate();

        // Nolasām ierakstus
        ResultSet rs = conn.prepareStatement("SELECT id, title, done FROM todo ORDER BY id").executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getBoolean("done"));
        }

        conn.close();
    }
}