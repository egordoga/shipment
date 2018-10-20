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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal quantity;
    private BigDecimal restQuantity;
    private BigDecimal delivQuantity;
    private BigDecimal price;
    private Byte isShip;
    private String characteristic;
    private String description;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "product_small_id")
    private ProductSmall productSmall;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;



    @Override
    public String toString() {
        return "Product{" +
                //"id=" + id +
                ", quantity=" + quantity +
                ", restQuantity=" + restQuantity +
                ", delivQuantity=" + delivQuantity +
                ", price=" + price +
                ", isShip=" + isShip +
                //", characteristic='" + characteristic + '\'' +
                ", description='" + description + '\'' +
                //", productBase=" + productBase +
                ", invoice=" + invoice +
                //", shipment=" + shipment +
                '}';
    }
}
