package ua.shipment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.shipment.utils.ParseNomenclature;

@Controller
public class LoadDbController {

    @Autowired
    private ParseNomenclature parseNomenclature;

    @GetMapping("/loadDB")
    public String loadDB() {
        parseNomenclature.parseProduct();
        return "/oops";
    }

    @GetMapping("/loadClient")
    public String loadClient() {
        parseNomenclature.parseClient();
        return "/oops";
    }

    @GetMapping("/loadDBSm")
    public String loadDBSm() {
        parseNomenclature.fillProductSmall();
        return "/oops";
    }

    /*@GetMapping("/load_db")
    public String viewLoadDb() {
        return "load_db";
    }*/

    @GetMapping("/del_zero")
    public String delZero() {
        parseNomenclature.delZero();
        return "/oops";
    }
}
