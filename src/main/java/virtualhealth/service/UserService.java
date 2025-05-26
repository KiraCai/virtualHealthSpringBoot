package virtualhealth.service;

import virtualhealth.model.Client;

import java.util.List;

public interface UserService {
    List<Client> findAllUsers();
    void addUser(Client client);
    Client findByEmail(String email);
    Client findByName(String firstName);
    Client updateUser(Client client);
    void deleteUserByEmail(String email);
}
