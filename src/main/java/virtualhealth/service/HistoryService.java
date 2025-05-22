package virtualhealth.service;

import virtualhealth.model.History;

import java.util.List;

public interface HistoryService {
    List<History> findAllHistory();
    History findByIdClient(Long idClient);
    void addHistory(History history);
    void deleteHistoryByUserId(Long idHistory);
}
