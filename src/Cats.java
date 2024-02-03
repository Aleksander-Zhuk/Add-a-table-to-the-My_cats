import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void delete_cat_id (int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
            String query = "DELETE FROM cats WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
            System.out.println("Строка с id = " + id + " удалена.");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void delete_cat_where (String where) {
        int quantity = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
            String query = "DELETE FROM cats WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,where+"%");
            quantity++;
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Удалено котиков : " + quantity);
    }

    public void update_cat (int id, String set, String where) {
        if (where.equals("type_id")) {
            try {
                int set_int = Integer.parseInt(set);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                String query = "UPDATE cats SET type_id = ? WHERE id = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,set_int);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return;
        }
        if (where.equals("name")) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                String query = "UPDATE cats SET name = ? WHERE id = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,set);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return;
        }
        if (where.equals("age")) {
            try {
                int set_int = Integer.parseInt(set);
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                String query = "UPDATE cats SET type_id = ? WHERE id = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,set_int);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (where.equals("weight")) {
            try {
                double set_int = Double.parseDouble((set));
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                String query = "UPDATE cats SET type_id = ? WHERE id = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setDouble(1,set_int);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void get_cat (int id) throws SQLException {
        String type_id = null;
        String name = null;
        int age = 0;
        double weight = 0.0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
            String query = "SELECT ty.type AS type,name ,age ,weight \n" +
                           "FROM cats ca JOIN types ty ON ca.type_id = ty.id \n" +
                           "WHERE ca.id = ? ";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                type_id = resultSet.getString("type");
                name = resultSet.getString("name");
                age = resultSet.getInt("age");
                weight = resultSet.getDouble("weight");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        System.out.println("Name : " + name );
        System.out.println("type_id : " + type_id);
        System.out.println("age : " + age);
        System.out.println("weight : " + weight + " кг.");
    }
    public void get_cat_where(String where) throws SQLException {
        List<Cats_info> cats_info = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String name = null;
        int age = 0;
        double weight = 0.0;
        int quantity = 0;

        String[] parts = where.split(" ");
        int number = Integer.parseInt(parts[0]);
        String letter = parts[1];

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
            String query = "SELECT ty.type AS type,name ,age ,weight \n" +
                           "FROM cats ca JOIN types ty ON ca.type_id = ty.id \n" +
                           "WHERE ca.id < ? AND name LIKE ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1,number);
            statement.setString(2,letter+"%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                name = resultSet.getString("name");
                age = resultSet.getInt("age");
                weight = resultSet.getDouble("weight");
                Cats_info catsInfo = new Cats_info(type, name, age, weight);
                cats_info.add(catsInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        for (Cats_info catsInfo : cats_info) {
            System.out.println("Type: " + catsInfo.getType());
            System.out.println("Name: " + catsInfo.getName());
            System.out.println("Age: " + catsInfo.getAge());
            System.out.println("Weight: " + catsInfo.getWeight());
            System.out.println();
            quantity++;
        }
        System.out.println(quantity);
    }
    public void get_all_cats() throws SQLException {

            List<Cats_info> cats_info = new ArrayList<>();
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String name = null;
            int age = 0;
            double weight = 0.0;
            int quantity = 0;

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats.db");
                String query = "SELECT ty.type AS type,name ,age ,weight \n" +
                        "FROM cats ca JOIN types ty ON ca.type_id = ty.id";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    name = resultSet.getString("name");
                    age = resultSet.getInt("age");
                    weight = resultSet.getDouble("weight");
                    Cats_info catsInfo = new Cats_info(type, name, age, weight);
                    cats_info.add(catsInfo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            for (Cats_info catsInfo : cats_info) {
                System.out.println("Type: " + catsInfo.getType());
                System.out.println("Name: " + catsInfo.getName());
                System.out.println("Age: " + catsInfo.getAge());
                System.out.println("Weight: " + catsInfo.getWeight());
                System.out.println();
                quantity++;
            }
        System.out.println(quantity);
    }
}

