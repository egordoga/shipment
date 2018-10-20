package ua.shipment.servicedb;

import ua.shipment.entity.Client;

import java.util.List;

public interface IClientService {

    Client findClientByName(String name);

    void saveClient(Client client);

    List<Client> findClientByNameShortSubstring(String sub);
}
