package ua.shipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.shipment.entity.Product;
import ua.shipment.entity.ProductBase;

import java.util.List;

@Repository
public interface ProductBaseRepository extends JpaRepository<ProductBase, Long> {
    ProductBase findFirstByVendorCode(String vendorCode);

    @Query("select pb from ProductBase pb where pb.vendorCode like %:substr%")
    List<ProductBase> findAllByString(@Param("substr") String substr);

    List<ProductBase> findAllByVendorCode(String vendorCode);
}
