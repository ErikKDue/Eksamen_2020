package datahandling;

import java.util.Map;

public interface SQLStatementBuilder {

   public final String CREATE = "CREATE";
    public final String READ = "READ";
    public final String UPDATE = "UPDATE";
    public final String DELETE = "DELETE";


    static String buildPreparedStatement(String type, Map<String, Object> elementsMap, Map<String, Object> PKMap, String table){

        switch (type){
            case CREATE:{
                String prefix = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" insert into ").append(table);
                stringBuilder.append(" (");
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append(key);
                }
                stringBuilder.append(")").append(" values (");
                prefix = "";
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append("?");
                }
                stringBuilder.append(")");
                return stringBuilder.toString();

            }

            case READ:{
                //"SELECT * FROM customer WHERE id =?";
                String prefix = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" SELECT * FROM ").append(table);
               /* stringBuilder.append(" SET ");
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append(key + "=?");
                }*/
                stringBuilder.append(" WHERE ");
                for (String key : PKMap.keySet()) {
                    stringBuilder.append(key +"=?");// here
                }
              /*  prefix = "";
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append("?");
                }
                stringBuilder.append(")");*/
                return stringBuilder.toString();



            }

            case UPDATE:{

                //"UPDATE student SET firstname=?, lastname=?, startdate=? WHERE studentid=?";
                String prefix = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" update ").append(table);
                stringBuilder.append(" SET ");
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append(key + "=?");
                }
                stringBuilder.append(" WHERE ");
                for (String key : PKMap.keySet()) {
                    stringBuilder.append(key +"=?");// here
                }
              /*  prefix = "";
                for (String key : elementsMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append("?");
                }
                stringBuilder.append(")");*/
                return stringBuilder.toString();

            }

            case DELETE:{
            // "DELETE FROM customer WHERE customerid =?";
                String prefix = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" delete from ").append(table); //delete from table
                stringBuilder.append(" where ");
                for (String key : PKMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append(key);
                }
                stringBuilder.append(" = ");
                prefix = "";
                for (String key : PKMap.keySet()) {
                    stringBuilder.append(prefix);
                    prefix = ",";
                    stringBuilder.append("?");
                }

                return stringBuilder.toString();


            }


        }
        return "";
    }
}
