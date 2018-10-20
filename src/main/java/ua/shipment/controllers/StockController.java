package ua.shipment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.shipment.entity.Shipment;
import ua.shipment.model.ShipmentPrintModel;
import ua.shipment.servicedb.ShipmentService;

import javax.swing.*;
import java.text.MessageFormat;

@Controller
public class StockController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/stock")
    public String viewStock() {
        return "stock";
    }

    @GetMapping("/shipments")
    public String showShipments(Model model) {
        model.addAttribute("shipmnts", shipmentService.findShipmentNoClose());
        return "shipments";
    }

    @GetMapping("/print")
    public String printShipment(Model model, @ModelAttribute("spmtId") String spmtId) {
        model.addAttribute("shipmnts", shipmentService.findShipmentNoClose());

        Shipment shipment = shipmentService.findShipmentById(Long.parseLong(spmtId));
        StringBuilder prn = new StringBuilder();
        prn.append("Номер заказа ").append(shipment.getInvoice().getNumber()).append("\n")
                .append("Покупатель ").append(shipment.getInvoice().getClient().getNameShort()).append("\n\n");
        /*for (ProductShip productShip : shipment.getProductShips()) {
            prn.append(productShip.getProductSmall().getVendorCode()).append("\t")
                    .append(productShip.getProductSmall().getName()).append("\t")
                    .append(productShip.getDelivQuantity()).append("\t")
                    .append(productShip.getDescription()).append("\n");
        }
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.append(prn.toString());*/

        ShipmentPrintModel spm = new ShipmentPrintModel(shipment.getProductShips());
        JTable prnTable = new JTable(spm);
        MessageFormat header = new MessageFormat(prn.toString());
        /*try {
            prnTable.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }*/

        //UsersTable ust = new UsersTable();
       /* PrintJob pjob = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "Печать", null);
        Graphics pg = pjob.getGraphics();
        pg.drawLine(20, 20, 1990, 20);
        pg.setFont(new Font("SansSerif", Font.PLAIN, 12));
        pg.drawString("Какойто текст", 25, 33);
        pg.drawLine(20, 40, 1990, 40);
        pg.translate(10, 50);
        prnTable.paint(pg);
        pg.dispose();
        pjob.end();*/
        return "shipments";
    }
}
