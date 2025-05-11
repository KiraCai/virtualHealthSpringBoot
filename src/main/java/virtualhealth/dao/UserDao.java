package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Client;

import java.util.List;

public interface UserDao extends JpaRepository<Client, Long> {
    //интерфейс, который Spring автоматически подключает как репозиторий для работы с базой данных
    Client findByEmail(String email);
    void deleteUserByEmail(String email);

}
