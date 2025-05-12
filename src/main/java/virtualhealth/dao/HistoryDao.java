package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.History;

public interface HistoryDao  extends JpaRepository<History, Long> {
    History findByClient_IdClient(Long idClient);
    int deleteByClient_IdClient(Long idClient);
}
