package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Long idConsultation;

    //history table
    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    //doctor table
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    Doctor doctor;

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
