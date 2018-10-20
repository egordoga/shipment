package ua.shipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.shipment.entity.ProductSmall;

import java.util.List;

@Repository
public interface ProductSmallRepository extends JpaRepository<ProductSmall, Long> {
    ProductSmall findByVendorCode(String code);

    List<ProductSmall> findProductSmallsByVendorCodeEndsWith(String end);

    List<ProductSmall> findAllByVendorCodeContains(String substr);

    List<ProductSmall> findAllByNameContains(String substr);

    @Query("from ProductSmall ps where ps.vendorCode like '%.0'")
    List<ProductSmall> findAllEndZero();
}
