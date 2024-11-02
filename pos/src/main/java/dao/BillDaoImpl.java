package dao;

import model.BillDetailsEntity;
import model.BillProductEntity;
import util.DBUtils;

import java.sql.*;
import java.util.List;

public class BillDaoImpl implements BillDao {
    private final Connection connection;

    public BillDaoImpl() {
        this.connection = DBUtils.getConnection();
    }

    @Override
    public String createBill(BillDetailsEntity billDetailsEntity, List<BillProductEntity> billProductEntityList) {
        try {
            connection.setAutoCommit(false);
            final String insertBillQuery = """
                    INSERT INTO t_bill_details(customer_name, customer_phone, billing_date, total_amount)
                    VALUES(?, ?, ?, ?)""";

            PreparedStatement preparedStatement = connection.prepareStatement(insertBillQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, billDetailsEntity.getCustomerName());
            preparedStatement.setString(2, billDetailsEntity.getCustomerPhone());
            preparedStatement.setDate(3, new Date(billDetailsEntity.getBillingDate().getTime()));
            preparedStatement.setBigDecimal(4, billDetailsEntity.getTotalAmount());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int billId = 0;
            if (resultSet.next()) {
                billId = resultSet.getInt(1);
            }

            resultSet.close();
            preparedStatement.close();


            final String insertBillProductQuery = """
                    INSERT INTO t_bill_product_mapping(bill_id, product_id, no_of_units)
                    VALUES(?, ?, ?)
                    """;

            final String productQtyUpdateQuery = """
                    UPDATE t_product_master SET qty = qty - ? WHERE id = ?;
                    """;

            preparedStatement = connection.prepareStatement(insertBillProductQuery);
            PreparedStatement ups = connection.prepareStatement(productQtyUpdateQuery);

            for (var x : billProductEntityList) {
                preparedStatement.setInt(1, billId);
                preparedStatement.setInt(2, x.getProductEntity().getId());
                preparedStatement.setInt(3, x.getNoOfUnits());

                ups.setInt(1, x.getNoOfUnits());
                ups.setInt(2, x.getProductEntity().getId());

                ups.addBatch();

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            ups.executeBatch();
            preparedStatement.clearBatch();
            ups.clearBatch();
            preparedStatement.close();
            ups.close();

            connection.commit();
            return "Transaction complete";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
