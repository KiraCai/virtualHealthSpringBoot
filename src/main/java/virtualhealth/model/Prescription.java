package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long idPrescription;

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

    @Column(name = "treatment")
    String treatment;

    @Column(name = "document")
    String document;
}
