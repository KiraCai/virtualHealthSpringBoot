package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.HistoryDao;
import virtualhealth.dao.UserDao;
import virtualhealth.model.Client;
import virtualhealth.model.History;
import virtualhealth.service.HistoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImp implements HistoryService {
    private final HistoryDao historyDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public List<History> findAllHistory() {
        return historyDao.findAll();
    }

    @Override
    public History findByIdClient(Long idClient){
        return historyDao.findByClient_IdClient(idClient);
    }

    @Override
    public void createHistoryByUserId(Long clientId){
        Client client = userDao.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
        History history = new History();
        history.setClient(client);
        historyDao.save(history);
    }

    @Override
    public void deleteHistoryByUserId(Long clientId){
        historyDao.deleteByClient_IdClient(clientId);
    }
}
