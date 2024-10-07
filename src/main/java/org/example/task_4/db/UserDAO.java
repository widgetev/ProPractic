package org.example.task_4.db;

import org.springframework.stereotype.Component;

import javax.naming.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.support.GenericApplicationContext;

@Component
public class UserDAO {
    private ConnectionPool connectionPool;
    private final Connection connection;

    public UserDAO(GenericApplicationContext context, ConnectionPool connectionPool) throws SQLException, ClassNotFoundException {
        this.connectionPool =context.getBean(ConnectionPool.class);
        this.connection =connectionPool.get();
        initDatabase(connection);
    }

    public Users get(Long id ) throws SQLException {
        Statement stmt = connection.createStatement();
        Users user = null;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users where id=" + id);
            if(rs.next()) {
                user = new Users(rs.getString("username"));
                user.setId((long) rs.getInt("id"));
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            stmt.close();
        }
        return user;
    }

    public List<Users> getAll() throws SQLException {
        Statement stmt = connection.createStatement();
        List<Users> usersList = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users ");
            while (rs.next()) {
                Users user = new Users(rs.getString("username"));
                user.setId((long) rs.getInt("id"));
                usersList.add(user);
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            stmt.close();
        }
        return usersList;
    }

    public void save(Users e) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            //не рассматриваю когда ID есть, а имени нету
            if (e.getId() == null) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM users where username='" + e.getUsername() + "'");
                if (!rs.next()) {
                    stmt.executeUpdate("INSERT INTO users (username) VALUES ('" + e.getUsername() + "')", Statement.RETURN_GENERATED_KEYS);
                    ResultSet generatedKeysResultSet = stmt.getGeneratedKeys();
                    generatedKeysResultSet.next();
                    e.setId((long) generatedKeysResultSet.getInt("id"));
                } else {//next() в if уже сделан. В БД уже не надо лезть
                    //такой user уже есть, впишу ID и оставлю как есть
                    e.setId((long) rs.getInt("id"));
                }
                rs.close();
            } else {
                throw new IllegalArgumentException("Только для новых пользователей");
                //this.update(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//
//    void update(Users e) throws SQLException {
//        try (Statement stmt = connection.createStatement()) {
//            stmt.executeUpdate("UPDATE users set username = '" + e.getUsername() + "' where id = " + e.getId(), Statement.RETURN_GENERATED_KEYS);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    public void delete(Users e) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + e.getUsername() + "'", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void initDatabase(Connection connection) throws ClassNotFoundException, SQLException {

        try (ResultSet tables = connection.getMetaData().getTables(null, null, "users", new String[] { "TABLE" })) {
            if(!tables.next()) {
                connection.createStatement().execute("CREATE TABLE users (id bigserial PRIMARY KEY, username varchar(255) unique);");
            }
        }

    }
}