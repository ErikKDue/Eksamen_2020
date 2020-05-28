package datahandling;

import java.util.Map;

public interface SQLStatementBuilder {

   public final String CREATE = "CREATE";
    public final String READ = "READ";
    public final String UPDATE = "UPDATE";
    public final String DELETE = "DELETE";


    static String buildPreparedStatement(String type, Map<String, Object> elementsMap, String table){

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


            }

            case UPDATE:{


            }

            case DELETE:{


            }


        }
        return "";
    }
}
