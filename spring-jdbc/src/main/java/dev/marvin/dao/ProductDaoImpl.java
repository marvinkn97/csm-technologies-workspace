package dev.marvin.dao;

import dev.marvin.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao{
    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper;

    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate, ProductRowMapper productRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRowMapper = productRowMapper;
    }

    @Override
    public Collection<Product> getAll() {
        final String sql = "SELECT id, name, qty, unit_price FROM t_product_master";

        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.forEach(System.out::println);

        return jdbcTemplate.query(sql, productRowMapper);
    }
}
