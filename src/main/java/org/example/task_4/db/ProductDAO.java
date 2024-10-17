package org.example.task_4.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ProductDAO.class.getName());
    private final Connection connection;
    private static final String SQL_SELECT_ALL = "SELECT id, accnum, sum::numeric, type, userid  FROM products";

    public ProductDAO(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public void save(Products products) {
        try (Statement stmt = connection.createStatement()) {
            //не рассматриваю когда ID есть, а имени нету
            if (products.getId() == null) {
                //ResultSet rs = stmt.executeQuery("SELECT * FROM products where username='" + e.getUsername() + "'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM products where accnum='" + products.getAccNum() + "'");
                if (!rs.next()) {
                    stmt.executeUpdate("INSERT INTO products (accnum, sum, type, userid) VALUES ('" + products.getAccNum() +
                            "', " + products.getSum() +
                            ", '"+ products.getType() + "'"+
                            ", " + products.getUserId() + " )", Statement.RETURN_GENERATED_KEYS);
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
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL +" where id=" + id);
            if(rs.next()) {
                product = getOneProduct(rs);
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
    private Products getOneProduct(ResultSet rs) throws SQLException {
        Products pr = new Products((long) rs.getInt("id"), rs.getString("accnum")
                , rs.getBigDecimal("sum")
                , ProductType.valueOf(rs.getString("type"))
                , rs.getLong("userid"));
        return pr;

    }

    public List<Products> getAllBySQL(String sql) {
        log.info("Call getAllBySQL SQL = " + sql);
        List<Products> productList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {;
                productList.add( getOneProduct(rs));
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    public List<Products> getByUserId(Long userId) {
        return getAllBySQL(SQL_SELECT_ALL + " where userId = " + userId);
    }

    public List<Products> getAll() {
        return getAllBySQL(SQL_SELECT_ALL);
    }

    public void delete(Products e) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM products WHERE accnum = '" + e.getAccNum() + "'", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}