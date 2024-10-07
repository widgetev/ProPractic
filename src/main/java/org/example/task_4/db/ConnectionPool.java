package org.example.task_4.db;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionPool {
    List<Connection> connectionList= new ArrayList<>(); //Пулл подразумевает несколько соединений. Но я с одинм поработаю
    private static DataSource dataSource;

    public ConnectionPool(DataSource dataSource) throws SQLException {
        ConnectionPool.dataSource =dataSource;
        connectionList.add(dataSource.getConnection());
    }

    public Connection get() {
        return connectionList.get(0);
    }
}
