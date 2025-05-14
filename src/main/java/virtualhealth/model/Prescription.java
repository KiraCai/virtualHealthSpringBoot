package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "prescription")
public class Prescription {
    //todo изменить тип для документа
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long idPrescription;

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

    @Column(name = "treatment")
    String treatment;

    @Column(name = "document")
    String document;

}
