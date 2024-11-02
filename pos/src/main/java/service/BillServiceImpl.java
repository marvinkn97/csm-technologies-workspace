package service;

import dao.BillDao;
import dao.BillDaoImpl;
import dto.BillDetailsDto;
import dto.BillProductDto;
import model.BillDetailsEntity;
import model.BillProductEntity;
import model.ProductEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillServiceImpl implements BillService {

    private final BillDao billDao;

    public BillServiceImpl() {
        this.billDao = new BillDaoImpl();
    }

    @Override
    public String createBill(BillDetailsDto billDetailsDto, List<BillProductDto> billProductDtoList) {
        System.out.println(billDetailsDto);
        System.out.println(billProductDtoList);
        BillDetailsEntity billDetailsEntity = new BillDetailsEntity();
        billDetailsEntity.setCustomerName(billDetailsDto.getCustomerName());
        billDetailsEntity.setCustomerPhone(billDetailsDto.getCustomerPhone());
        billDetailsEntity.setBillingDate(new Date());
        billDetailsEntity.setTotalAmount(new BigDecimal(billDetailsDto.getTotalAmount()));

        List<BillProductEntity> billProductEntityList = new ArrayList<>();

        billProductDtoList
                .forEach(x -> {
                    BillProductEntity billProductEntity = new BillProductEntity();
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setId(Integer.parseInt(x.getProductDto().getId()));
                    productEntity.setName(x.getProductDto().getName());
                    productEntity.setQuantity(Integer.parseInt(x.getProductDto().getQuantity()));
                    productEntity.setUnitPrice(new BigDecimal(x.getProductDto().getUnitPrice()));
                    billProductEntity.setProductEntity(productEntity);
                    billProductEntity.setNoOfUnits(Integer.parseInt(x.getNoOfUnits()));
                    billProductEntityList.add(billProductEntity);
                });
        return billDao.createBill(billDetailsEntity, billProductEntityList);
    }
}
