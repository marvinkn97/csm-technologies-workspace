package dev.marvin.dao;

import dev.marvin.model.ProductEntity;
import dev.marvin.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ProductDaoJdbcImpl implements ProductDao {
    private static final Connection connection = DBUtil.getConnection();

    @Override
    public Integer create(ProductEntity product) {
        final String sql = "INSERT INTO tbl_products(product_name, unit_price, mfg_date) VALUES(?, ?, ?)";
        int generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.productName());
            statement.setBigDecimal(2, product.unitPrice());
            statement.setDate(3, new Date(product.manufacturingDate().getTime()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("unknown error occurred: " + e.getMessage());

        }
        return generatedId;
    }

    @Override
    public Collection<ProductEntity> getAllProducts() {
        final String sql = "SELECT product_id, product_name, unit_price, mfg_date FROM tbl_products";
        Collection<ProductEntity> products = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                products = new ArrayList<>();
                do {
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("product_name");
                    BigDecimal unitPrice = resultSet.getBigDecimal("unit_price");
                    Date mfgDate = resultSet.getDate("mfg_date");
                    ProductEntity productEntity = new ProductEntity(productId, productName, unitPrice, new java.util.Date(mfgDate.getTime()));
                    products.add(productEntity);
                } while (resultSet.next());
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("unknown error occurred: " + e.getMessage());
        }

        return products;
    }

    @Override
    public Integer delete(String productId) {
        final String sql = "DELETE FROM tbl_products WHERE product_id = ?";
        int rowsAffected = 0;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, Integer.parseInt(productId));
           rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("unknown error occurred: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public Integer update(ProductEntity product) {
        final String sql = "UPDATE tbl_products SET product_name = ?, unit_price = ?, mfg_date = ?  WHERE product_id = ?";
        int rowsAffected = 0;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, product.productName());
            preparedStatement.setBigDecimal(2, product.unitPrice());
            preparedStatement.setDate(3, new java.sql.Date(product.manufacturingDate().getTime()));
            preparedStatement.setInt(4, product.productId());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("unknown error occurred: " + e.getMessage());
        }
        return rowsAffected;
    }
}
