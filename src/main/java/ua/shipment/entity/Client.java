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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String nameShort;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices;

    public Client(String name, String nameShort) {
        this.name = name;
        this.nameShort = nameShort;
    }
}
