package virtualhealth.service;

import virtualhealth.model.Client;
import virtualhealth.model.History;

import java.util.List;

public interface HistoryService {
    List<History> findAllHistory(); //не работает
    History findByIdClient(Long idClient); //работает
    void createHistoryByUserId(Long clientId); //работает
    void deleteHistoryByUserId(Long clientId);
}
