package ua.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String vendorCode;
    private String nameShort;
    private String name;

   /* @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_small_id ")
    private ProductSmall productSmall;*/


    public ProductBase(String code, String vendorCode, String nameShort, String name) {
        this.code = code;
        this.vendorCode = vendorCode;
        this.nameShort = nameShort;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductBase{" +
               // ", code='" + code + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                ", nameShort='" + nameShort + '\'' +
               // ", name='" + name + '\'' +
               // ", product=" + product +
                '}';
    }
}
