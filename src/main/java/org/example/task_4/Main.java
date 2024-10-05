package org.example.task_4;


import org.example.task_4.db.ConnectionPool;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.sql.*;

@ComponentScan
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(Main.class);
        Connection connection = context.getBean(ConnectionPool.class).get();
        initDatabase(connection);

    }

    public static void initDatabase(Connection connection) throws ClassNotFoundException, SQLException {

        try (ResultSet tables = connection.getMetaData().getTables(null, null, "users", new String[] { "TABLE" })) {
            if(!tables.next()) {
                connection.createStatement().execute("CREATE TABLE users (id bigserial PRIMARY KEY, username varchar(255) unique);");
            }
        }

//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("select datname from pg_database where datname like 'task4';");
//        String databaseName = "";
//        if(rs.next()) {
//            databaseName = rs.getString("datname");
//            stmt.executeUpdate("DROP DATABASE " + databaseName, Statement.NO_GENERATED_KEYS);
//        }

//
//        try {
//            stmt.execute("CREATE TABLE users (id bigserial PRIMARY KEY\n" +
//                                            "    , username varchar(255) unique);");
//        }catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            stmt.close();
//            connection.close();
//        }
//

    }

}
