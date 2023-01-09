package com.projekt.DAO;

import com.projekt.DatabaseConnector;
import com.projekt.entity.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDao implements Dao<Ticket> {
    private final Connection connection;

    public TicketDao() throws SQLException {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        this.connection = dbConnector.connect();
    }

    @Override
    public Ticket get(int id) {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM trains_management_db.tickets INNER JOIN trains_management_db.users USING (idUser) INNER JOIN trains_management_db.trains USING (idTrain)";

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
                    LocalTime departureTime = resultSet.getObject("departureTime", LocalTime.class);
                    LocalTime arrivalTime = resultSet.getObject("arrivalTime", LocalTime.class);
                    int platform = resultSet.getInt("platform");
                    Date date = resultSet.getDate("date");
                    // ZMIENIĆ NA FABRYKE - TRZEBA ZROBIĆ KOD 'KLIENTCKI'
                    Train train = new PassengerTrain(idTrain, trainNumber,from, to, departureTime, arrivalTime, platform, date);

                    tickets.add(new Ticket(train, user, idTicket, seatNumber, price));
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {

    }

    @Override
    public void update(Ticket ticket, String[] params) {

    }

    @Override
    public void delete(Ticket ticket) {

    }
}
