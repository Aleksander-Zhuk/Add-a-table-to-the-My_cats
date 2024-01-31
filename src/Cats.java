import java.io.*;
import java.sql.*;
import java.util.ArrayList;

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
    public void  add_more_cats (int n) {
        int quantity = 0;
        String fileName = "D:/Изображения/ЗАГРУЗКИ/names.txt";
        ArrayList<String> namesList = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] names = line.split(" ");
                for (String name : names) {
                    namesList.add(name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] names_cat = namesList.toArray(new String[0]);
        int l = names_cat.length;
                for (int a = 0; a < n; a++) {
                    int type = 1 + (int) (Math.random() * 62);
                    int name = (int) (Math.random() * l );
                    int age = 1 + (int) (Math.random() * 6);
                    double weight = Math.round((2 + (Math.random() * 5)) * 10.0) / 10.0;
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                        String sql = "INSERT INTO cats (name, type_id, age, weight)" +
                                "VALUES (?, ?, ?, ?)" ;
                        conn.prepareStatement(sql);
                        PreparedStatement statement;
                        statement = conn.prepareStatement(sql);
                        statement.setString(1, names_cat[name]);
                        statement.setInt(2, type);
                        statement.setInt(3, age);
                        statement.setDouble(4, weight);
                        statement.executeUpdate();
                        conn.close();
                        statement.close();
                        quantity++;
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
        System.out.println("Котик(и) добавлен(ы) в количечтве: " + quantity);
    }
}

