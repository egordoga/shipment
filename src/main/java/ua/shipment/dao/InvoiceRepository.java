package ua.shipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shipment.entity.Client;
import ua.shipment.entity.Invoice;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByDate(LocalDateTime date);
    List<Invoice> findAllByIsClosed(Byte isClosed);

    List<Invoice> findAllByIsClosedAndClient(Byte isClosed, Client client);
}
