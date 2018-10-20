package ua.shipment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.shipment.entity.Invoice;
import ua.shipment.entity.Product;
import ua.shipment.entity.ProductShip;
import ua.shipment.entity.Shipment;
import ua.shipment.servicedb.InvoiceService;
import ua.shipment.servicedb.ProductService;
import ua.shipment.servicedb.ShipmentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class TableController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private ProductService productService;

    @GetMapping("/delivery")
    public String viewInvoice(Model model, @ModelAttribute("invId") String invId) {
        return "delivery";
    }

    @PostMapping("/delivery")
    public String saveShipmnt(@ModelAttribute("inv") Invoice inv, @ModelAttribute("shipm") Shipment shipment) {
        shipment.setInvoice(inv);
        shipment.setDate(LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        shipment.setDateFormat(LocalDateTime.now().format(formatter));
        shipment.setProductShips(new ArrayList<>());
        shipment.setIsFinished((byte) 0);

        for (Product prod : inv.getProducts()) {
            if (prod.getDelivQuantity().compareTo(BigDecimal.ZERO) > 0) {
                prod.setRestQuantity(prod.getRestQuantity().subtract(prod.getDelivQuantity()));
                if (prod.getRestQuantity().compareTo(BigDecimal.ZERO) == 0) {
                    prod.setIsShip((byte) 1);
                }
                ProductShip prodShip = new ProductShip();
                prodShip.setProductSmall(prod.getProductSmall());
                prodShip.setDelivQuantity(prod.getDelivQuantity());
                prodShip.setCharacteristic(prod.getCharacteristic());
                prodShip.setDescription(prod.getDescription());
                prodShip.setShipment(shipment);

                shipment.getProductShips().add(prodShip);
                prod.setDelivQuantity(BigDecimal.ZERO);
            }
        }
        shipmentService.saveShipment(shipment);
        invoiceService.saveInvoice(inv);
        return "redirect:/invoices";
    }

    @ModelAttribute("inv")
    public Invoice getInvoice(@ModelAttribute("invId") String invId) {
        return invoiceService.findInvoiceById(Long.parseLong(invId));
    }

    @ModelAttribute("shipm")
    public Shipment getShipment() {
        return new Shipment();
    }


}
