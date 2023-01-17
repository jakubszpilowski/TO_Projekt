package com.projekt.DAO;

import com.projekt.DatabaseConnector;
import com.projekt.entity.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;

public class TicketDao implements Dao<Ticket> {
    private final Connection connection;

    public TicketDao() {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        this.connection = dbConnector.connect();
    }

    @Override
    public Ticket get(int id) {
        String sql = "SELECT * FROM trains_management_db.tickets INNER JOIN trains_management_db.users USING (idUser) INNER JOIN trains_management_db.trains USING (idTrain) WHERE idTicket = ?";
        Ticket ticket;
        Train train;
        TrainFactory trainFactory;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idTrain = resultSet.getInt("idTrain");
                    int idUser = resultSet.getInt("idUser");
                    int idTicket = resultSet.getInt("idTicket");

                    int seatNumber = resultSet.getInt("seatNumber");
                    double price = resultSet.getDouble("price");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    double balance = resultSet.getDouble("balance");
                    User user = new User(idUser, name, surname, email, balance);

                    int trainNumber = resultSet.getInt("trainNumber");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    String type = resultSet.getString("type");
                    LocalTime departureTime = resultSet.getObject("departureTime", LocalTime.class);
                    LocalTime arrivalTime = resultSet.getObject("arrivalTime", LocalTime.class);
                    int platform = resultSet.getInt("platform");
                    Date date = resultSet.getDate("date");

                    if (Objects.equals(type, "pasażerski")) {
                        trainFactory = new PassengerTrainFactory();
                    } else if (Objects.equals(type, "towarowy")) {
                        trainFactory = new CargoTrainFactory();
                    } else {
                        System.out.println("Wrong type " + type + " in database!");
                        return null;
                    }
                    train = trainFactory.createTrain(idTrain, trainNumber, from,
                            to, departureTime, arrivalTime, platform, date);
                    return new Ticket(train, user, idTicket, seatNumber, price);
                }
                else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM trains_management_db.tickets INNER JOIN trains_management_db.users USING (idUser) INNER JOIN trains_management_db.trains USING (idTrain)";
        TrainFactory trainFactory;
        Train train;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idTrain = resultSet.getInt("idTrain");
                    int idUser = resultSet.getInt("idUser");
                    int idTicket = resultSet.getInt("idTicket");

                    int seatNumber = resultSet.getInt("seatNumber");
                    double price = resultSet.getDouble("price");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    double balance = resultSet.getDouble("balance");
                    User user = new User(idUser, name, surname, email, balance);

                    int trainNumber = resultSet.getInt("trainNumber");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    String type = resultSet.getString("type");
                    LocalTime departureTime = resultSet.getObject("departureTime", LocalTime.class);
                    LocalTime arrivalTime = resultSet.getObject("arrivalTime", LocalTime.class);
                    int platform = resultSet.getInt("platform");
                    Date date = resultSet.getDate("date");

                    if (Objects.equals(type, "pasażerski")) {
                        trainFactory = new PassengerTrainFactory();
                    } else if (Objects.equals(type, "towarowy")) {
                        trainFactory = new CargoTrainFactory();
                    } else {
                        System.out.println("Wrong type " + type + " in database!");
                        continue;
                    }
                    train = trainFactory.createTrain(idTrain, trainNumber, from,
                            to, departureTime, arrivalTime, platform, date);
                    tickets.add(new Ticket(train, user, idTicket, seatNumber, price));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return tickets;
    }

    @Override
    public void add(Ticket ticket) {
        String sql = "INSERT INTO trains_management_db.tickets (idTicket, idUser, idTrain, seatNumber, price) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getTicketId());
            statement.setInt(2, ticket.getUser().getUserId());
            statement.setInt(3, ticket.getTrain().getTrainId());
            statement.setInt(4, ticket.getSeatNumber());
            statement.setDouble(5, ticket.getPrice());
            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Ticket ticket, TreeMap<String, Object> params) {

    }

    @Override
    public void delete(Ticket ticket) {
        String sql = "DELETE FROM trains_management_db.tickets WHERE idTicket = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getTicketId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
