package ua.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductSmall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String vendorCode;
    private String vendorCodeMy;

    /*@OneToMany(mappedBy = "productSmall")
    private List<ProductBase> productBases;*/

    @OneToMany(mappedBy = "productSmall")
    private List<Product> products;

    @OneToMany(mappedBy = "productSmall")
    private List<ProductShip> productShips;

    @Override
    public String toString() {
        return "ProductSmall{" +
               // "id=" + id +
                ", name='" + name + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                //", vendorCodeMy='" + vendorCodeMy + '\'' +
                //", products=" + products +
                //", productShips=" + productShips +
                '}';
    }
}
