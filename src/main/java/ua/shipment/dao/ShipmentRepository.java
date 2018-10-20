package ua.shipment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shipment.entity.Shipment;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {


    List<Shipment> findAllByIsFinished(Byte isFinished);
}
