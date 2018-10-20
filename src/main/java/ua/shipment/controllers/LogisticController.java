package ua.shipment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.shipment.servicedb.InvoiceService;

@Controller
public class LogisticController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/logistic")
    public String viewLogistic() {
        return "logistic";
    }

    @GetMapping("/invoices")
    public String viewInvoices(Model model) {
        model.addAttribute("invs", invoiceService.allInvoice());
        return "invoices";
    }

}
