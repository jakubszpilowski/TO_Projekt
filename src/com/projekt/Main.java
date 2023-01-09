package com.projekt;

import com.projekt.entity.CargoTrainFactory;
import com.projekt.entity.PassengerTrainFactory;
import com.projekt.entity.Train;
import com.projekt.entity.TrainFactory;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) throws ParseException {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        try {
            dbConnector.connect();
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }

        Launcher launcher = new Launcher();
        launcher.run();

        // Stworzenie pociągu pasażerskiego za pomocą metody wytwórczej
//        TrainFactory factory = new PassengerTrainFactory();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Train passengerTrain = factory.createTrain(8, 155, "Warszawa", "Berlin",
//                LocalTime.parse("10:15:30"), LocalTime.parse("15:45:21"),
//                22, df.parse("2023-01-08"));

        // Stworzenie pociągu towarowego za pomocą metody wytwórczej
//        factory = new CargoTrainFactory();
//        Train cargoTrain = factory.createTrain(10, 155, "Warszawa", "Berlin",
//                LocalTime.parse("11:15:30"), LocalTime.parse("16:45:21"),
//                22, df.parse("2023-01-08"));
    }
}
