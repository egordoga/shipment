package ua.shipment.servicedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shipment.dao.ClientRepository;
import ua.shipment.entity.Client;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findClientByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findClientByNameShortSubstring(String sub) {
        return clientRepository.findAllByNameShortContains(sub);
    }
}
