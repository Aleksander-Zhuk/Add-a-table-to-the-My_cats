import java.sql.*;

public class Cats {
    public Cats() {
        int type = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db")) {
            String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='cats'";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 1) {
                        type = 1;
                        System.out.println("Таблица уже существует.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (type == 0) {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db")) {
                String sql = "CREATE TABLE IF NOT EXISTS cats (\n"
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,\n"
                        + " name VARCHAR(20) NOT NULL,\n"
                        + " type_id INTEGER, \n"
                        + " age INTEGER NOT NULL,\n"
                        + " weight DOUBLE,\n"
                        + "FOREIGN KEY (type_id)  REFERENCES types (id)"
                        + ");";
                assert conn != null;
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    conn.close();
                    System.out.println("таблица создана");
                }
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

