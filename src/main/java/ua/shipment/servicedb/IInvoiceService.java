package ua.shipment.servicedb;

import ua.shipment.entity.Client;
import ua.shipment.entity.Invoice;

import java.time.LocalDateTime;
import java.util.List;

public interface IInvoiceService {

    void saveInvoice(Invoice invoice);

    List<Invoice> allInvoice();
    List<Invoice> findAllInvoiceByDate(LocalDateTime date);
    List<Invoice> findAllInvoiceByIsClosed(Byte isClosed);

    Invoice findInvoiceById(Long id);

    List<Invoice> findInvoicesNoClosedByClient(Client client);
}
