package datahandling;

import annotations.StoreableAttribute;
import annotations.StoreablePKey;
import dat19d.group.six.motorhomerental.datahandling.DBManager;
import dat19d.group.six.motorhomerental.model.Customer;
import dat19d.group.six.motorhomerental.model.IStoreable;
import dat19d.group.six.motorhomerental.model.MotorHome;
import dat19d.group.six.motorhomerental.model.RentalPeriod;
import datahandling.consumers.ConsumerGenerator;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static datahandling.SQLStatementBuilder.CREATE;
import static datahandling.SQLStatementBuilder.READ;
import static datahandling.SQLStatementBuilder.UPDATE;
import static datahandling.SQLStatementBuilder.DELETE;

public class GenericMapper {

    public static void create(IStoreable input) {
        Class clazz = input.getClass();




        try {
            Map<String, Object> classFieldsMap = getClassFieldMap(input, new HashMap<>());
            Map<String, Object> classPrimaryKeyMap = getClassPrimaryKeyMap(input, new HashMap<>());

            Connection connection = DBManager.getConnection();
            String sql = SQLStatementBuilder.buildPreparedStatement(CREATE, classFieldsMap, classPrimaryKeyMap, input.getType());
            PreparedStatement statement = connection.prepareStatement(sql);
            //needs to get a list of fields from the class

            Map<Integer, Object> values = new HashMap<>();
            int x = 1;
            for (String key : classFieldsMap.keySet()) {
                values.put(x, classFieldsMap.get(key));
                x++;
            }

            for (Integer key : values.keySet()) {
                statement.setObject(key, values.get(key));
            }
            statement.execute();
            //rowsAffected = statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {


            }
        }

    }
    @Deprecated
    public static void customerCreate(IStoreable input) {
        Class clazz = input.getClass();
        switch (clazz.getName()){
            case "model.Customer":
                System.out.println(clazz.getName());
            case "model.MotorHome":
                System.out.println(clazz.getName());
        }
        Customer customer = (Customer) input;
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO customer (ID, first_name, last_name, address, phone, email) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.customerID);
            statement.setString(2, customer.firstName);
            statement.setString(3, customer.lastName);
            statement.setString(4, customer.address);
            statement.setInt(5, 12341234);
            statement.setString(6, customer.email);
            statement.execute();
            //rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            if (e instanceof SQLIntegrityConstraintViolationException) {


            }
        }

    }


    public static IStoreable read(IStoreable input){
        Class clazz = input.getClass();
        //Customer customer = (Customer) input;

        try {
            Map<String, Object> classFieldsMap = getClassFieldMap(input, new HashMap<>());
            Map<String, Object> classPrimaryKeyMap = getClassPrimaryKeyMap(input, new HashMap<>());

            Connection connection = DBManager.getConnection();
            String sql = SQLStatementBuilder.buildPreparedStatement(READ, classFieldsMap, classPrimaryKeyMap, input.getType());
            //String sql = "SELECT * FROM customer WHERE id =?";

            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setInt(1, customer.customerID);
            Map<Integer, Object> values = new HashMap<>();
            int x = 1; //I've left this using loops for now
            for (String key : classPrimaryKeyMap.keySet()) {
                values.put(x, classPrimaryKeyMap.get(key));
                x++;
            }

            for (Integer key : values.keySet()) {
                statement.setObject(key, values.get(key));
            }
            //statement.execute();
            //Statement statement = connection.createStatement();
            //String sql = "SELECT * FROM dept WHERE deptno =?" ;
            ResultSet rs = statement.executeQuery();


            //this is a quick and ugly implementation
            switch (clazz.getName()){
                case "dat19d.group.six.motorhomerental.model.Customer":{
                if (rs.next()) {
                    int id = rs.getInt("customerid");
                    String firstName2 = rs.getString("firstname");
                    String lastName2 = rs.getString("lastname");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    return new Customer(id, firstName2, lastName2, address, phone, email);}
                }
                case "dat19d.group.six.motorhomerental.model.MotorHome":{
                    if (rs.next()) {
                        //registrationnumber
                        String registrationNumber = rs.getString("registrationnumber");
                        //model
                        String model = rs.getString("model");
                        //brand
                        String brand = rs.getString("brand");
                        //baseprice
                        double basePrice = rs.getDouble("baseprice");
                        //capacity
                        int capacity = rs.getInt("capacity");
                        return new MotorHome(registrationNumber, brand, model, capacity, basePrice);}
                }
                case "dat19d.group.six.motorhomerental.model.RentalPeriod":{
                    if (rs.next()) {
                        //id
                        int id = rs.getInt("id");
                        //customerID
                        int customerID = rs.getInt("customerid");
                        //registrationnumber
                        String registrationnumber = rs.getString("registrationnumber");
                        //startdate
                        LocalDate startDate = rs.getDate("startdate").toLocalDate();
                        //enddate
                        LocalDate endDate = rs.getDate("enddate").toLocalDate();
                        Customer customer = (Customer)ConsumerGenerator.getAndRunConsumer(new Customer(customerID), READ);
                        MotorHome motorHome = (MotorHome)ConsumerGenerator.getAndRunConsumer(new MotorHome(registrationnumber), READ);
                        return new RentalPeriod(id, customer, motorHome, 0, 0, startDate, endDate);}
                }
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Customer();

    }

    @Deprecated
    public static IStoreable readCustomer(IStoreable input){

        Customer customer = (Customer) input;

        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM customer WHERE id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.customerID);
            //Statement statement = connection.createStatement();
            //String sql = "SELECT * FROM dept WHERE deptno =?" ;
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                String firstName2 = rs.getString("first_name");
                String lastName2 = rs.getString("last_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                return new Customer(id, firstName2, lastName2, address, phone, email);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customer();

    }

    public static void update(IStoreable input) {
        Class clazz = input.getClass();




        try {
            Map<String, Object> classFieldsMap = getClassFieldMap(input, new HashMap<>());
            Map<String, Object> classPrimaryKeyMap = getClassPrimaryKeyMap(input, new HashMap<>());

            Connection connection = DBManager.getConnection();
            String sql = SQLStatementBuilder.buildPreparedStatement(UPDATE, classFieldsMap, classPrimaryKeyMap, input.getType());
            PreparedStatement statement = connection.prepareStatement(sql);


            Map<Integer, Object> values = new HashMap<>();
            int x = 1;
            for (String key : classFieldsMap.keySet()) {
                values.put(x, classFieldsMap.get(key));
                x++;
            }
            for (String key : classPrimaryKeyMap.keySet()) {
                values.put(x, classPrimaryKeyMap.get(key));
                x++;
            }

            for (Integer key : values.keySet()) {
                statement.setObject(key, values.get(key));
            }
            statement.execute();
            //rowsAffected = statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            if (e instanceof SQLIntegrityConstraintViolationException) {


            }
        }

    }

    public static void delete(IStoreable input) {
        //Customer customer = (Customer) input;
        try {
            Map<String, Object> classFieldsMap = getClassFieldMap(input, new HashMap<>()); //technically not necessary
            Map<String, Object> classPrimaryKeyMap = getClassPrimaryKeyMap(input, new HashMap<>());


            Connection connection = DBManager.getConnection();
            String sql = SQLStatementBuilder.buildPreparedStatement(DELETE, classFieldsMap, classPrimaryKeyMap, input.getType());
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setInt(1, customer.customerID);
            Map<Integer, Object> values = new HashMap<>();
            int x = 1; //I've left this using loops for now
            for (String key : classPrimaryKeyMap.keySet()) {
                values.put(x, classPrimaryKeyMap.get(key));
                x++;
            }

            for (Integer key : values.keySet()) {
                statement.setObject(key, values.get(key));
            }
            statement.execute();

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();

            }
        }

        @Deprecated
        public static void deleteCustomer(IStoreable input) {
        Customer customer = (Customer) input;
        try {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM customer WHERE customerid =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.customerID);
            //Statement statement = connection.createStatement();
            //String sql = "SELECT * FROM dept WHERE deptno =?" ;
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    static Map<String, Object> getClassFieldMap(IStoreable itemToStore, Map<String, Object> returnMap) throws IllegalAccessException {
        Field[] fields = getDeclaredFieldsFromClazz(itemToStore);
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(StoreableAttribute.class)) {
                if(field.getType().getTypeName().equals("java.lang.String")) {
                    returnMap.put((field.getName()), (String) field.get(itemToStore));
                } else if(field.getType().getTypeName().equals("java.time.LocalDate")){
                    returnMap.put(field.getName(), java.sql.Date.valueOf((LocalDate)field.get(itemToStore)));

                } else {
                    returnMap.put(field.getName(), field.get(itemToStore));
                }
            }
        }
        return returnMap;
    }

    static Map<String, Object> getClassPrimaryKeyMap(IStoreable itemToStore, Map<String, Object> returnMap) throws IllegalAccessException {
        Field[] fields = getDeclaredFieldsFromClazz(itemToStore);
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(StoreablePKey.class)) {
                if(field.getType().getTypeName().equals("java.lang.String")) {
                    returnMap.put((field.getName()), (String) field.get(itemToStore));
                } else if(field.getType().getTypeName().equals("java.time.LocalDate")){
                    returnMap.put(field.getName(), java.sql.Date.valueOf((LocalDate)field.get(itemToStore)));
                } else {
                    returnMap.put(field.getName(), field.get(itemToStore));
                }
            }
        }
        return returnMap;
    }

    static Field[] getDeclaredFieldsFromClazz(Object itemToStore){
        Class<?> clazz = itemToStore.getClass();
        return clazz.getDeclaredFields();
    }
}

