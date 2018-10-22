package ua.shipment.servicedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shipment.dao.ProductRepository;
import ua.shipment.entity.Client;
import ua.shipment.entity.Product;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProductByStringWendor(String substr) {
        return productRepository.findProductsByIsShipAndProductSmall_VendorCodeContains((byte) 0, substr);
    }

    @Override
    public List<Product> findAllProductByIsShip(Byte isShip) {
        return productRepository.findAllByIsShip(isShip);
    }

    @Override
    public List<Product> findAllProdNoShipAndSubstrByVendorCode(String substr) {
        //return productRepository.findAllNoShipAndSubstrByVendorCode(substr);
        return null;
    }

    @Override
    public List<Product> findProductsByProductBaseVendorCodeContainsAndIsShip(String sub, Byte b) {
        // return productRepository.findProductsByProductBaseVendorCodeContainsAndIsShip(sub, b);
        return null;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsByIsShipAndInvoiceId(Long invId) {
        return productRepository.findAllByIsShipAndInvoice_Id((byte) 0, invId);
    }

    @Override
    public List<Product> groupByNoShipProdsByClient(Client client) {
        return productRepository.findAllByIsShipAndInvoice_Client((byte) 0, client);
    }
}
