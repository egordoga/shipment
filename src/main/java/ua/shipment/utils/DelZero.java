package ua.shipment.utils;

import ua.shipment.entity.ProductSmall;
import ua.shipment.servicedb.ProductSmallService;

import java.util.List;

public class DelZero {

    public static void main(String[] args) {

        ProductSmallService pss = new ProductSmallService();
        List<ProductSmall> list = pss.findEndZero();
        for (ProductSmall productSmall : list) {
            String v = productSmall.getVendorCode();
            productSmall.setVendorCode(v.substring(0, v.length() - 2));
            pss.saveProductSmall(productSmall);
        }
    }
}
