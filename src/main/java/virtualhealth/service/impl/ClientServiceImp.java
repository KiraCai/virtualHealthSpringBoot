package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import virtualhealth.dao.UserDao;
import virtualhealth.model.Client;
import virtualhealth.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImp implements UserService {
    //второй уровень использует третий сервис - дао
    private final UserDao repository;
    @Override
    public List<Client> findAllUsers() {
        return repository.findAllUsers();
    }
    @Override
    public void addUser(Client client) {
        repository.addUser(client);
    }
    @Override
    public Client findByEmail(String email) {
        return repository.findByEmail(email);
    }
    @Override
    public Client updateUser(Client client) {
        return repository.updateUser(client);
    }
    @Override
    public void deleteUserByEmail(String email){
        repository.deleteUserByEmail(email);
    }
}
