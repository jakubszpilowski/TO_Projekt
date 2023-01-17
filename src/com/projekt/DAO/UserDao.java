package com.projekt.DAO;

import com.projekt.DatabaseConnector;
import com.projekt.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

public class UserDao implements Dao<User> {
    private final Connection connection;

    public UserDao() throws SQLException {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        this.connection = dbConnector.connect();
    }

    @Override
    public User get(int id) {
        String sql = "SELECT * FROM trains_management_db.users WHERE idUser = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    double balance = resultSet.getDouble("balance");
                    return new User(id, name, surname, email, balance);
                } else {
                    return null;
                }
            }
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user, TreeMap<String, Object> params) {

    }

    @Override
    public void delete(User user) {

    }
}
