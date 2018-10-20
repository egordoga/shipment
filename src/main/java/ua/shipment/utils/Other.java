package ua.shipment.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.shipment.entity.Product;
import ua.shipment.entity.ProductBase;
import ua.shipment.entity.ProductSmall;
import ua.shipment.servicedb.ProductSmallService;

import java.util.List;

@Component
public class Other {

    @Autowired
    static ProductSmallService pss;


    public static void main(String[] args) {
        //ProductSmallService pss = new ProductSmallService();
        /*List<ProductSmall> list = pss.findEndZero();
        System.out.println(list.get(0));*/

        System.out.println(pss.findProductSmallByVendorCode("W70.V04"));
    }
}
