/*package virtualhealth.dao;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class ClientDaoImp implements UserDao<Client, Long>{*/

   /* private final List<Client> CLIENTS = new ArrayList<Client>();
    @Override
    public List<Client> findAllUsers() {
        return CLIENTS;
    }
    @Override
    public void addUser(Client client) {
        CLIENTS.add(client);
    }*/
    /*@Override
    public Client findByEmail(String email) {
        return CLIENTS.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    //}
    /*@Override
    public Client updateUser(Client client) {
        //индекс студента у которого электронная почта совпадает с почтой студента из вызова метода
        var clientIndex = IntStream.range(0, CLIENTS.size())
                .filter(index -> CLIENTS.get(index).getEmail().equals(client.getEmail()))
                .findFirst()
                .orElse(-1);
        if (clientIndex > -1) {
            CLIENTS.set(clientIndex, client);
            return client;
        }
        return null;
    }*/
    /*@Override
    public void deleteUserByEmail(String email){
        var client = findByEmail(email);
        if(client != null) {
            CLIENTS.remove(client);
        }
    }
}*/
