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
                        conn.close();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (type == 0) {
            try  {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
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
    public void insert_cat (String name, String type, int age, double weight){
        String type_test = "";
        ResultSet resultSet = null;
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
            String sql = "SELECT type FROM types WHERE type = ?";
            conn.prepareStatement(sql);
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, type);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                type_test = resultSet.getString("type");
            }
            conn.close();
            statement.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            if (type_test.equals(type)) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                    String sql2 = "INSERT INTO cats (name, type_id, age, weight)" +
                            "VALUES (?, (SELECT id FROM types WHERE type = ?), ?, ?)" ;
                    conn.prepareStatement(sql2);
                    PreparedStatement statement;
                    statement = conn.prepareStatement(sql2);
                    statement.setString(1, name);
                    statement.setString(2, type);
                    statement.setInt(3, age);
                    statement.setDouble(4, weight);
                    statement.executeUpdate();
                    conn.close();
                    statement.close();
                    System.out.println("Котик добавлен");
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                    String sql2 = "INSERT INTO types (type) VALUES (?)" ;
                    conn.prepareStatement(sql2);
                    PreparedStatement statement;
                    statement = conn.prepareStatement(sql2);
                    statement.setString(1, type);
                    statement.executeUpdate();
                    conn.close();
                    statement.close();
                    System.out.println("Тип котика добавлен");
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                    String sql2 = "INSERT INTO cats (name, type_id, age, weight)" +
                            "VALUES (?, (SELECT id FROM types WHERE type = ?), ?, ?)" ;
                    conn.prepareStatement(sql2);
                    PreparedStatement statement;
                    statement = conn.prepareStatement(sql2);
                    statement.setString(1, name);
                    statement.setString(2, type);
                    statement.setInt(3, age);
                    statement.setDouble(4, weight);
                    statement.executeUpdate();
                    conn.close();
                    statement.close();
                    System.out.println("Котик добавлен");
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
    }
}

