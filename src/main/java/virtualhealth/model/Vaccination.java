package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "vaccination")
public class Vaccination {
    //todo и изменить тип для документа
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private Long idVaccination;

    //doctor table
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    Doctor doctor;

    //history table
    @ManyToOne
    @JoinColumn(name="history_id", nullable=false)
    private History history;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "name_vac")
    String nameVac;

    @Column(name = "place")
    String place;

    @Column(name = "reason")
    String reason;

    @Column(name = "document")
    String document;

}
