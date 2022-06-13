package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "153191Vn";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT id,name,email,country FROM users WHERE id = ?";

    private static final String SELECT_ALL_USERS = "SELECT * FROM users";

    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";

    private static final String UPDATE_USERS_SQL = "UPDATE users SET name = ?, email= ?, country =? WHERE id = ?;";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection con = getConnection(); PreparedStatement pre = con.prepareStatement(INSERT_USERS_SQL)) {
            pre.setString(1, user.getName());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getCountry());
            System.out.println(pre);
            pre.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection con = getConnection();
             // Step 2:Create a statement using con object
             PreparedStatement pre = con.prepareStatement(SELECT_USER_BY_ID);) {
            pre.setInt(1, id);
            System.out.println(pre);
            // Step 3: Execute the query or update query
            ResultSet rs = pre.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection con = getConnection();

             // Step 2:Create a statement using connection object

             PreparedStatement pre = con.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(pre);
            // Step 3: Execute the query or update query
            ResultSet rs = pre.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection con = getConnection(); PreparedStatement pre = con.prepareStatement(DELETE_USERS_SQL);) {
            pre.setInt(1, id);
            rowDeleted = pre.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection con = getConnection(); PreparedStatement pre = con.prepareStatement(UPDATE_USERS_SQL);) {
            pre.setString(1, user.getName());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getCountry());
            pre.setInt(4, user.getId());

            rowUpdated = pre.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
