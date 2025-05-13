package virtualhealth.service;

import virtualhealth.model.History;

import java.util.List;

public interface HistoryService {
    List<History> findAllHistory();
    History findByIdClient(Long idClient);
    void createHistoryByUserId(Long clientId);
    void deleteHistoryByUserId(Long idHistory);
}
