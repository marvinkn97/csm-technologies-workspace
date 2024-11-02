package service;

import dto.BillDetailsDto;
import dto.BillProductDto;

import java.util.List;

public interface BillService {
    String createBill(BillDetailsDto billDetailsDto, List<BillProductDto> billProductDtoList);
}
