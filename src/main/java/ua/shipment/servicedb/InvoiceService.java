package ua.shipment.servicedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shipment.dao.InvoiceRepository;
import ua.shipment.entity.Client;
import ua.shipment.entity.Invoice;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> allInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> findAllInvoiceByDate(LocalDateTime date) {
        return invoiceRepository.findByDate(date);
    }

    @Override
    public List<Invoice> findAllInvoiceByIsClosed(Byte isClosed) {
        return invoiceRepository.findAllByIsClosed(isClosed);
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return invoiceRepository.getOne(id);
    }

    @Override
    public List<Invoice> findInvoicesNoClosedByClient(Client client) {
        return invoiceRepository.findAllByIsClosedAndClient((byte) 0, client);
    }
}
