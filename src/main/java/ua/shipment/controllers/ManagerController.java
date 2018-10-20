package ua.shipment.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.shipment.servicedb.InvoiceService;
import ua.shipment.utils.ParseInvoice;

import java.io.File;
import java.io.IOException;

@Controller
public class ManagerController {

    @Autowired
    private ParseInvoice parseInvoice;
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/manager")
    public String viewManager() {
        return "manager";
    }

    @PostMapping("/upload")
    public String uploadInvoice(@RequestParam("file") MultipartFile multFile) {
        File file = new File(multFile.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(file, multFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseInvoice.verifyFile(file);
        return "redirect:/manager";
    }

    @GetMapping("/no_ship_invs")
    public String viewNoShipInvs(Model model) {
        model.addAttribute("invs", invoiceService.findAllInvoiceByIsClosed((byte) 0));
        return "no_ship_invoices";
    }

    @GetMapping("/no_ship_deliv")
    public String viewNoShipDeliv(Model model, @ModelAttribute("invId") String invId) {
        model.addAttribute("inv", invoiceService.findInvoiceById(Long.parseLong(invId)));
        return "no_ship_delivery";
    }
}
