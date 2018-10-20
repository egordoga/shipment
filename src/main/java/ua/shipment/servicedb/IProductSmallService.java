package ua.shipment.servicedb;

import ua.shipment.entity.ProductSmall;

import java.util.List;

public interface IProductSmallService {
    ProductSmall saveProductSmall(ProductSmall productSmall);

    ProductSmall findProductSmallByVendorCode(String code);

    List<ProductSmall> findEndZero();

    List<ProductSmall> findAllProdsSmallByVendorCodeSubstr(String substr);
    List<ProductSmall> findAllProdsSmallByNameSubstr(String substr);
}

