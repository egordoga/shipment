package ua.shipment.servicedb;

import ua.shipment.entity.Client;
import ua.shipment.entity.Product;

import java.util.List;

public interface IProductService {

    void saveProduct(Product product);
    List<Product> findAllProductByStringWendor(String substr);
    List<Product> findAllProductByIsShip(Byte isShip);
    List<Product> findAllProdNoShipAndSubstrByVendorCode(String substr);
    List<Product> findProductsByProductBaseVendorCodeContainsAndIsShip(String sub, Byte b);
    List<Product> findAllProduct();
    List<Product> findAllProductsByIsShipAndInvoiceId(Long invId);

    List<Product> groupByNoShipProdsByClient(Client client);
}
