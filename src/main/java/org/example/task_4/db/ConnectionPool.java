package org.example.task_4.db;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionPool {
    List<Connection> connectionList= new ArrayList<>();;
    private final DataSource dataSource;

    public ConnectionPool(DataSource dataSource) throws ClassNotFoundException, SQLException {
        this.dataSource =dataSource;
        this.connectionList.add(dataSource.getConnection());
    }

    public Connection get() {
        return connectionList.get(0);
    }
}
