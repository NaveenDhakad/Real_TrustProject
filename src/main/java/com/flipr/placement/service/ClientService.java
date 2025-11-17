package com.flipr.placement.service;

import com.flipr.placement.model.Client;
import com.flipr.placement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client existingClient = client.get();
            existingClient.setName(clientDetails.getName());
            existingClient.setDescription(clientDetails.getDescription());
            existingClient.setDesignation(clientDetails.getDesignation());
            if (clientDetails.getImageUrl() != null) {
                existingClient.setImageUrl(clientDetails.getImageUrl());
            }
            if (clientDetails.getImageData() != null) {
                existingClient.setImageData(clientDetails.getImageData());
            }
            return clientRepository.save(existingClient);
        }
        return null;
    }
}
