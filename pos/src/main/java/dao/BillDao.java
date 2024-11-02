package dao;

import model.BillDetailsEntity;
import model.BillProductEntity;

import java.util.List;

public interface BillDao {
    String createBill(BillDetailsEntity billDetailsEntity, List<BillProductEntity> billProductEntityList);
}
