package org.example.task_4.db;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private final Connection connection;
    public ProductDAO(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
        initDatabase(connection);
    }


    public void save(Products products) {
        try (Statement stmt = connection.createStatement()) {
            //не рассматриваю когда ID есть, а имени нету
            if (products.getId() == null) {
                //ResultSet rs = stmt.executeQuery("SELECT * FROM products where username='" + e.getUsername() + "'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM products where accnum='" + products.getAccNum() + "'");
                if (!rs.next()) {
                    stmt.executeUpdate("INSERT INTO products (accnum, sum, type) VALUES ('" + products.getAccNum() +
                            "', " + products.getSum() +
                            ", '"+ products.getType() + "' )", Statement.RETURN_GENERATED_KEYS);
                    ResultSet generatedKeysResultSet = stmt.getGeneratedKeys();
                    generatedKeysResultSet.next();
                    products.setId((long) generatedKeysResultSet.getInt("id"));
                } else {//next() в if уже сделан. В БД уже не надо лезть
                    //такой user уже есть, впишу ID и оставлю как есть
                    products.setId((long) rs.getInt("id"));
                }
                rs.close();
            } else {
                throw new IllegalArgumentException("Только для новых пользователей");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Products get(Long id ){
        Products product = null;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM products where id=" + id);
            if(rs.next()) {
                product = new Products(rs.getString("accnum")
                        , rs.getDouble("sum")
                        , ProductType.valueOf(rs.getString("type")));
                product.setId((long) rs.getInt("id"));
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public List<Products> getAll() {
        List<Products> productList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM products ");
            while (rs.next()) {
                Products product = new Products(rs.getString("accnum")
                        , rs.getDouble("sum")
                        , ProductType.valueOf(rs.getString("type")));
                product.setId((long) rs.getInt("id"));
                productList.add(product);
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }


    public void delete(Products e) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM products WHERE accnum = '" + e.getAccNum() + "'", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void initDatabase(Connection connection) throws SQLException {
        try (ResultSet tables = connection.getMetaData().getTables(null, null, "products", new String[] { "TABLE" })) {
            if(!tables.next()) {
                connection.createStatement().execute("CREATE TABLE products (id bigserial PRIMARY KEY, accnum varchar(25) unique, sum money, type varchar(4));");
            }
        }

    }
}