package ua.shipment.servicedb;

import ua.shipment.entity.Client;
import ua.shipment.entity.Invoice;
import ua.shipment.entity.ProductBase;

import java.util.List;

public interface IProductBaseService {

    void saveProductBase(ProductBase productBase);
    ProductBase findFirstProductBaseByVendorCode(String vendorCode);
    List<ProductBase> findAllProductBaseByVendorCode(String vendorCode);
    List<ProductBase> findAllProductBase();




}
