package ua.shipment.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdForm {

    private String vendorCode;
    private String name;
    private BigDecimal sum;

    public ProdForm(String vendorCode, String name, BigDecimal sum) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.sum = sum;
    }
}
