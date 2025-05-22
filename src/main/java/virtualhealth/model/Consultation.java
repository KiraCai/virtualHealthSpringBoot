package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    private History history;

    //doctor table
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    @ToString.Exclude
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
