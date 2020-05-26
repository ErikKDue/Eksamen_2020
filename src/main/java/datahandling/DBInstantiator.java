package datahandling;

import model.IStoreable;

import java.sql.*;

public class DBInstantiator {

    public static void createSchema() {
        //gets called if the DBManager can't find the right schema
        System.out.println("Trying to create schema.");
        System.out.println(createTables(DBManager.getBasicConnection(), "CREATE DATABASE `nordicmotorhome_g6` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;\n", "Schema created."));
        System.out.println(createTables(DBManager.getConnection(), "\n" +
                "CREATE TABLE IF NOT EXISTS `unavailability` (\n" +
                "  `date` date NOT NULL,\n" +
                "  `motor_home_id` varchar(20) NOT NULL,\n" +
                "  `status_id` int NOT NULL,\n" +
                "  PRIMARY KEY (`date`,`motor_home_id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n", "unavailability created."));
        System.out.println(createTables(DBManager.getConnection(), "CREATE TABLE IF NOT EXISTS `motor_home` (\n" +
                "  `license` varchar(20) NOT NULL,\n" +
                "  `model` varchar(45) NOT NULL,\n" +
                "  `brand` varchar(45) NOT NULL,\n" +
                "  `price` decimal(20,0) NOT NULL,\n" +
                "  `capacity` int NOT NULL,\n" +
                "  PRIMARY KEY (`license`),\n" +
                "  UNIQUE KEY `license_UNIQUE` (`license`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;", "motor_home created."));
        System.out.println(createTables(DBManager.getConnection(), "CREATE TABLE IF NOT EXISTS `customer` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `first_name` varchar(45) NOT NULL,\n" +
                "  `last_name` varchar(45) NOT NULL,\n" +
                "  `email` varchar(45) NOT NULL,\n" +
                "  `phone` int NOT NULL,\n" +
                "  `address` varchar(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `id_UNIQUE` (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;", "Customer created"));
        System.out.println(createTables(DBManager.getConnection(), "CREATE TABLE IF NOT EXISTS `rental_period` (\n" +
                "  `id` int NOT NULL,\n" +
                "  `customer` int NOT NULL,\n" +
                "  `motor_home` varchar(20) NOT NULL,\n" +
                "  `start_date` date NOT NULL,\n" +
                "  `end_date` date NOT NULL,\n" +
                "  `rental_period` varchar(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `customer_idx` (`customer`),\n" +
                "  KEY `motor_home_idx` (`motor_home`),\n" +
                "  CONSTRAINT `customer` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "  CONSTRAINT `motor_home` FOREIGN KEY (`motor_home`) REFERENCES `motor_home` (`license`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;", "rental period created"));
    }

    public static String createTables(Connection connection2, String s, String s2) {
        try {
            Connection connection = connection2;
            String sql = s;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            //rowsAffected = statement.executeUpdate();
            return(s2);

            //connection.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            if (e instanceof SQLIntegrityConstraintViolationException) {


            }
        }
        return ("it didn't work :(");
    }

}
