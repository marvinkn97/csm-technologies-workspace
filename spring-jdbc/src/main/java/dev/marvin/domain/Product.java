package dev.marvin.domain;

import java.math.BigDecimal;

public record Product(Integer id, String name, Integer quantity, BigDecimal unitPrice) {
}
