package ua.shipment.servicedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shipment.dao.ProductSmallRepository;
import ua.shipment.entity.ProductSmall;

import java.util.List;

@Service
public class ProductSmallService implements IProductSmallService {

    @Autowired
    private ProductSmallRepository productSmallRepository;

    @Override
    public ProductSmall saveProductSmall(ProductSmall productSmall) {
        return productSmallRepository.save(productSmall);
    }

    @Override
    public ProductSmall findProductSmallByVendorCode(String code) {
        return productSmallRepository.findByVendorCode(code);
    }

    @Override
    public List<ProductSmall> findEndZero() {
        return productSmallRepository.findProductSmallsByVendorCodeEndsWith(".0");
    }

    @Override
    public List<ProductSmall> findAllProdsSmallByVendorCodeSubstr(String substr) {
        return productSmallRepository.findAllByVendorCodeContains(substr);
    }

    @Override
    public List<ProductSmall> findAllProdsSmallByNameSubstr(String substr) {
        return productSmallRepository.findAllByNameContains(substr);
    }
}
