package ua.shipment.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime date;
    private String dateFormat;
    private String comment;
    private Byte isFinished;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<ProductShip> productShips;

    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Invoice invoice;

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", products=" + productShips +
                ", invoice=" + invoice +
                '}';
    }
}
