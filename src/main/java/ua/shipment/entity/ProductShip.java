package ua.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductShip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal delivQuantity;
    private String characteristic;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_small_id")
    private ProductSmall productSmall;
}
