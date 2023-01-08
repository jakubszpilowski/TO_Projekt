package com.projekt.DAO;

import com.projekt.DatabaseConnector;
import com.projekt.entity.Train;

import java.sql.*;
import java.util.List;

public class TrainDao implements Dao<Train>{
    private final Connection connection;

    public TrainDao() throws SQLException {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        this.connection = dbConnector.connect();
    }

    @Override
    public Train get(int id) {
        /*
        String sql = "SELECT * FROM trains_management_db.trains WHERE idTrain = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idTrain = resultSet.getInt("idTrain");
                    int trainNumber = resultSet.getInt("trainNumber");
                    String type = resultSet.getString("type");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    Time departureTime = resultSet.getTime("departureTime");
                    Time arrivalTime = resultSet.getTime("arrivalTime");;
                    int platform = resultSet.getInt("platform");;
                    Date date = resultSet.getDate("date");
                    int numberOfSeats = resultSet.getInt("numberOfSeats");

                    return new Train();
                } else {
                    return null;
                }
            }
        }
        catch (SQLException e) {
            return null;
        }
        */
        return null;
    }

    @Override
    public List<Train> getAll() {
        return null;
    }

    @Override
    public void save(Train train) {

    }

    @Override
    public void update(Train train, String[] params) {

    }

    @Override
    public void delete(Train train) {

    }
}
