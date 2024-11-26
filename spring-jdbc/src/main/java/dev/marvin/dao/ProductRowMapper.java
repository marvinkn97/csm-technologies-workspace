package dev.marvin.dao;

import dev.marvin.domain.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("qty"), rs.getBigDecimal("unit_price"));
    }
}
