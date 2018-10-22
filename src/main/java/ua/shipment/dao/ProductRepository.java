package ua.shipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shipment.entity.Client;
import ua.shipment.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIsShip(Byte isShip);

    List<Product> findProductsByIsShipAndProductSmall_VendorCodeContains(Byte b, String substr);

    List<Product> findAllByIsShipAndInvoice_Id(Byte isShip, Long invId);

    List<Product> findAllByIsShipAndInvoice_Client(Byte b, Client client);

    /*@Query("select new ua.shipment.model.ProdForm(s.vendorCode, s.nameShort, sum(p.restQuantity)) " +
            "from Product p join p.productSmall s join p.invoice i join i.client c where c.nameShort like :str group by s.vendorCode")
    List<ProdForm> groupNoShipByClient(String substr);*/
}
