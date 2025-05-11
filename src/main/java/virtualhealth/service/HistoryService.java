package virtualhealth.service;

import virtualhealth.model.Client;
import virtualhealth.model.History;

import java.util.List;

public interface HistoryService {
    List<History> findAllHistory(); //todo не работает
    History findByIdClient(Long idClient); //todo не работает
    void createHistoryByUserId(Long clientId); //работает
    void deleteHistoryByUserId(Long clientId);
}
