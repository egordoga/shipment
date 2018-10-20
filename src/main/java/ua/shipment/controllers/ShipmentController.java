package ua.shipment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.shipment.servicedb.ShipmentService;

@Controller
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/shipment")
    public String viewShipment(Model model, @ModelAttribute("spmtId") String spmtId) {
        model.addAttribute("shipment", shipmentService.findShipmentById(Long.parseLong(spmtId)));
        return "shipment";
    }
}
