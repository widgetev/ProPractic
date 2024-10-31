package org.example.task_4.db;

//import org.example.task_4.db.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.SQLException;
/*import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;*/

@Component
@Deprecated
public class ProductDAO {
    private static final Logger log = LoggerFactory.getLogger(ProductDAO.class.getName());
    private final Connection connection;
    private static final String SQL_SELECT_ALL = "SELECT id, accnum, sum::numeric, type, userid  FROM products";

    public ProductDAO(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }
/*
    public void save(ProductDTO product) {
        try (Statement stmt = connection.createStatement()) {
            //не рассматриваю когда ID есть, а имени нету
            if (product.getId() == null) {
                //ResultSet rs = stmt.executeQuery("SELECT * FROM products where username='" + e.getUsername() + "'");
                ResultSet rs = stmt.executeQuery("SELECT * FROM products where accnum='" + product.getAccNum() + "'");
                if (!rs.next()) {
                    stmt.executeUpdate("INSERT INTO products (accnum, sum, type, userid) VALUES ('" + product.getAccNum() +
                            "', " + product.getSum() +
                            ", '"+ product.getType() + "'"+
                            ", " + product.getUserId() + " )", Statement.RETURN_GENERATED_KEYS);
                    ResultSet generatedKeysResultSet = stmt.getGeneratedKeys();
                    generatedKeysResultSet.next();
                    product.setId((long) generatedKeysResultSet.getInt("id"));
                } else {//next() в if уже сделан. В БД уже не надо лезть
                    //такой user уже есть, впишу ID и оставлю как есть
                    product.setId((long) rs.getInt("id"));
                }
                rs.close();
            } else {
                throw new IllegalArgumentException("Только для новых пользователей");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ProductDTO get(Long id ){
        ProductDTO product = null;
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
    private ProductDTO getOneProduct(ResultSet rs) throws SQLException {
        ProductDTO pr = new ProductDTO((long) rs.getInt("id"), rs.getString("accnum")
                , rs.getBigDecimal("sum")
                , ProductTypeDTO.valueOf(rs.getString("type"))
                , rs.getLong("userid"));
        return pr;

    }

    public List<ProductDTO> getAllBySQL(String sql) {
        log.info("Call getAllBySQL SQL = " + sql);
        List<ProductDTO> productList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {;
                productList.add(getOneProduct(rs));
            }
            rs.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    public List<ProductDTO> getByUserId(Long userId) {
        return getAllBySQL(SQL_SELECT_ALL + " where userId = " + userId);
    }
    public List<ProductDTO> getByAccNum(Long userId, String accnum) {
        return getAllBySQL(SQL_SELECT_ALL + " where userId = " + userId + " and accnum = '" + accnum + "'");
    }
    public ProductDTO getByProductIdUserId(Long pid, Long userId) {
        List<ProductDTO> productsList = getAllBySQL(SQL_SELECT_ALL + " where id = "+ pid + " and userId = " + userId);
        if(productsList.size()>0)
            return productsList.get(0);
        return null;
    }

    public List<ProductDTO> getAll() {
        return getAllBySQL(SQL_SELECT_ALL);
    }

    //Обновить можно только остаток. Для всего остального - создавай новый счет
    public ProductDTO update(ProductDTO product) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("UPDATE products set sum = " + product.getSum() + " where id = " + product.getId(), Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
        return product;
    }

    public void delete(ProductDTO e) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM products WHERE accnum = '" + e.getAccNum() + "'", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/
}