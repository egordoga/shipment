package ua.shipment.servicedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shipment.dao.ProductBaseRepository;
import ua.shipment.entity.ProductBase;

import java.util.List;

@Service
public class ProductBaseService implements IProductBaseService {

    @Autowired
    private ProductBaseRepository productBaseRepository;

    @Override
    public void saveProductBase(ProductBase productBase) {
        productBaseRepository.save(productBase);
    }

    @Override
    public ProductBase findFirstProductBaseByVendorCode(String vendorCode) {
        return productBaseRepository.findFirstByVendorCode(vendorCode);
    }

    @Override
    public List<ProductBase> findAllProductBaseByVendorCode(String vendorCode) {
        return productBaseRepository.findAllByVendorCode(vendorCode);
    }

    @Override
    public List<ProductBase> findAllProductBase() {
        return productBaseRepository.findAll();
    }


}
