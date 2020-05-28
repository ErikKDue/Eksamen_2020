package datahandling;

import annotations.StoreableAttribute;
import dat19d.group.six.motorhomerental.datahandling.DBManager;
import dat19d.group.six.motorhomerental.model.Customer;
import dat19d.group.six.motorhomerental.model.IStoreable;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static datahandling.SQLStatementBuilder.CREATE;

public class GenericMapper {

    public static void create(IStoreable input) {
        Class clazz = input.getClass();




        try {
            Map<String, Object> classFieldsMap = getClassFieldMap(input, new HashMap<>());

            Connection connection = DBManager.getConnection();
            String sql = SQLStatementBuilder.buildPreparedStatement(CREATE, classFieldsMap, input.getType());
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


    public static void delete(IStoreable input) {
        Customer customer = (Customer) input;
        try {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM customer WHERE ID =?";
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
                if(field.getType().getTypeName().equals("java.lang.String")) { //might be okay to remove?
                    returnMap.put((field.getName()), (String) field.get(itemToStore));
                } else if(field.getType().getTypeName().equals("java.time.LocalDate")){
                    returnMap.put(field.getName(), java.sql.Date.valueOf((LocalDate)field.get(itemToStore)));
                    //java.sql.Date.valueOf( localDate );
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

