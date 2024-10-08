package dev.marvin.model;

import java.math.BigDecimal;
import java.util.Date;

public record ProductEntity(
        Integer productId,
        String productName,
        BigDecimal unitPrice,
        Date manufacturingDate
) {}
