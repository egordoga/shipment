package ua.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private LocalDateTime date;
    private String dateFormat;
    private Byte isView;
    private Byte isClosed;

    @ManyToOne(cascade = CascadeType.ALL)       //????????????????????????????
    @JoinColumn(name = "person_id")
    private Person responsiblePerson;

    @ManyToOne(cascade = CascadeType.ALL)       //????????????????????????????
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "invoice")
    private List<Shipment> shipments;

    public Invoice(String number, LocalDateTime date, Person responsiblePerson, Client client, List<Product> products) {
        this.number = number;
        this.date = date;
        this.responsiblePerson = responsiblePerson;
        this.client = client;
        this.products = products;
    }


}


