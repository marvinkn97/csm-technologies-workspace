package dao;

import model.ProductEntity;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl() {
        this.connection = DBUtils.getConnection();
    }

    @Override
    public Collection<ProductEntity> getAll() {
        final String sql = "SELECT id, name, qty, unit_price FROM t_product_master";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<ProductEntity> products = new ArrayList<>();
                do {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setId(rs.getInt("id"));
                    productEntity.setName(rs.getString("name"));
                    productEntity.setQuantity(rs.getInt("qty"));
                    productEntity.setUnitPrice(rs.getBigDecimal("unit_price"));
                    products.add(productEntity);
                } while (rs.next());
                return products;
            }
        } catch (SQLException e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
    }
}
