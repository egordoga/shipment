package ua.shipment.servicedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shipment.dao.ShipmentRepository;
import ua.shipment.entity.Shipment;

import java.util.List;

@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public void saveShipment(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> findShipmentNoClose() {
        return shipmentRepository.findAllByIsFinished((byte) 0);
    }

    @Override
    public Shipment findShipmentById(Long spmId) {
        return shipmentRepository.getOne(spmId);
    }
}
