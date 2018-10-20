package ua.shipment.servicedb;

import ua.shipment.entity.Shipment;

import java.util.List;

public interface IShipmentService {

    void saveShipment(Shipment shipment);

    List<Shipment> findShipmentNoClose();

    Shipment findShipmentById(Long spmId);
}
