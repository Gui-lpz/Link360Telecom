package controller;

import java.util.ArrayList;
import model.data.ClientData;
import model.entities.Client;

public class ClientController {

    private ClientData clientData;

    public ClientController() {
        clientData = new ClientData();
    }

    public void addClient(Client client) throws Exception {
        clientData.add(client);
    }

    public Client findClientById(int id) throws Exception {
        return clientData.findById(id);
    }

    public ArrayList<Client> getAllClients() throws Exception {
        return clientData.getAll();
    }

    public void updateClient(Client client) throws Exception {
        clientData.update(client);
    }

    public void deleteClient(int id) throws Exception {
        clientData.delete(id);
    }
}