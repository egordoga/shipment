package ua.shipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.shipment.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByIsShip(Byte isShip);

    List<Product> findProductsByIsShipAndProductSmall_VendorCodeContains(Byte b, String substr);

    List<Product> findAllByIsShipAndInvoice_Id(Byte isShip, Long invId);

    List<Product> findAllByIsShipAndInvoice_Client_NameShortContains(Byte b, String substr);

    @Query("select new ua.shipment.model.ProdForm(p.productSmall, ps.name, sum(p.restQuantity) " +
            "from Product p join p.productSmall ps where ps.name")
    List<Product> hh();
}
