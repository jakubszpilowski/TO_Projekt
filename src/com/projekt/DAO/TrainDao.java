package com.projekt.DAO;

import com.projekt.DatabaseConnector;
import com.projekt.entity.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class TrainDao implements Dao<Train>{
    private final Connection connection;

    public TrainDao() {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        this.connection = dbConnector.connect();
    }

    @Override
    public Train get(int id) {
        String sql = "SELECT * FROM trains_management_db.trains WHERE idTrain = ?";
        TrainFactory trainFactory;
        Train train;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idTrain = resultSet.getInt("idTrain");
                    int trainNumber = resultSet.getInt("trainNumber");
                    String type = resultSet.getString("type");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    LocalTime departureTime = resultSet.getTime("departureTime").toLocalTime();
                    LocalTime arrivalTime = resultSet.getTime("arrivalTime").toLocalTime();
                    int platform = resultSet.getInt("platform");;
                    Date date = resultSet.getDate("date");
                    int numberOfSeats = resultSet.getInt("numberOfSeats");

                    if (Objects.equals(type, "pasażerski")) {
                        trainFactory = new PassengerTrainFactory();
                    } else if (Objects.equals(type, "towarowy")) {
                        trainFactory = new CargoTrainFactory();
                    } else {
                        System.out.println("Wrong type " + type + " in database!");
                        return null;
                    }
                    return trainFactory.createTrain(idTrain, trainNumber, from,
                            to, departureTime, arrivalTime, platform, date);
                } else {
                    return null;
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Train> getAll() {
        String sql = "SELECT * FROM trains_management_db.trains";
        TrainFactory trainFactory;
        List<Train> trains = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idTrain = resultSet.getInt("idTrain");
                    int trainNumber = resultSet.getInt("trainNumber");
                    String type = resultSet.getString("type");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    LocalTime departureTime = resultSet.getTime("departureTime").toLocalTime();
                    LocalTime arrivalTime = resultSet.getTime("arrivalTime").toLocalTime();
                    int platform = resultSet.getInt("platform");;
                    Date date = resultSet.getDate("date");
                    int numberOfSeats = resultSet.getInt("numberOfSeats");

                    if (Objects.equals(type, "pasażerski")) {
                        trainFactory = new PassengerTrainFactory();
                    } else if (Objects.equals(type, "towarowy")) {
                        trainFactory = new CargoTrainFactory();
                    } else {
                        System.out.println("Wrong type " + type + " in database!");
                        continue;
                    }
                    trains.add(trainFactory.createTrain(idTrain, trainNumber, from,
                            to, departureTime, arrivalTime, platform, date));
                }
                return trains;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(Train train) {
        String sql = "INSERT INTO trains_management_db.trains (trainNumber, type, `from`, `to`, departureTime, " +
                "arrivalTime, platform, date, numberOfSeats)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getTrainNumber());
            statement.setString(2, train.getTrainType());
            statement.setString(3, train.getFrom());
            statement.setString(4, train.getTo());
            statement.setTime(5, Time.valueOf(train.getDepartureTime()));
            statement.setTime(6, Time.valueOf(train.getArrivalTime()));
            statement.setInt(7, train.getPlatform());
            statement.setDate(8, new java.sql.Date(train.getDate().getTime()));
            statement.setInt(9, train.getNumberOfSeats());
            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Train train, TreeMap<String, Object> params) {
        String sql = "UPDATE trains_management_db.trains" +
                " SET trainNumber = ?, type = ?," +
                " `from` = ?, `to` = ?, departureTime = ?," +
                " arrivalTime = ?, platform = ?, date = ?," +
                " numberOfSeats = ?" +
                " WHERE idTrain = ?;";
        Train previousState = train;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, params.containsKey("trainNumber") ?
                    (int) params.get("trainNumber") : previousState.getTrainNumber());

            statement.setString(2, params.containsKey("type") ?
                    (String) params.get("type") : previousState.getTrainType());

            statement.setString(3, params.containsKey("from") ?
                    (String) params.get("from") : previousState.getFrom());

            statement.setString(4, params.containsKey("to") ?
                    (String) params.get("to") : previousState.getTo());

            statement.setTime(5, params.containsKey("departureTime") ?
                            Time.valueOf((LocalTime)params.get("departureTime")) :
                            Time.valueOf(previousState.getDepartureTime()));

            statement.setTime(6, params.containsKey("arrivalTime") ?
                    Time.valueOf((LocalTime)params.get("arrivalTime")) :
                    Time.valueOf(previousState.getArrivalTime()));

            statement.setInt(7, params.containsKey("platform") ?
                    (int) params.get("platform") : previousState.getPlatform());

            statement.setDate(8, params.containsKey("date") ?
                    (java.sql.Date)params.get("date") :
                    new java.sql.Date(previousState.getDate().getTime()));

            statement.setInt(9, params.containsKey("numberOfSeats") ?
                    (int) params.get("numberOfSeats") : previousState.getNumberOfSeats());

            statement.setInt(10, previousState.getTrainId());

            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Train train) {
        String sql = "DELETE FROM trains_management_db.trains WHERE idTrain = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train.getTrainId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
