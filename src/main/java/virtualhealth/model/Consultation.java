package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "consultation")
public class Consultation {
    //todo добавить ссылку на таблицу доктора 1:1 и изменить тип для документа
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Long idConsultation;

    //history table
    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "complaints")
    String complaints;

    @Column(name = "decision")
    String decision;

    @Column(name = "document")
    String document;
}
