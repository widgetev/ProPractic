package org.example.task_4.db;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Component
public class UserDAO {
    private final Connection connection;

    public UserDAO(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public Users get(Long id ){
        Users user = null;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users where id=" + id);
            if(rs.next()) {
                user = new Users(rs.getString("username"));
                user.setId((long) rs.getInt("id"));
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public List<Users> getAll() {
        List<Users> usersList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users ");
            while (rs.next()) {
                Users user = new Users(rs.getString("username"));
                user.setId((long) rs.getInt("id"));
                usersList.add(user);
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usersList;
    }

    public void save(Users e) {
        try (Statement stmt = connection.createStatement()) {
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
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Users e) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + e.getUsername() + "'", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}