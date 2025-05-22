package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long idTest;

    //history table
    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    @ToString.Exclude
    private History history;

    //doctor table
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude
    private Doctor doctor;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "name_test")
    String nameTest;

    @Column(name = "place")
    String place;

    @Column(name = "result")
    String result;

    @Column(name = "reason")
    String reason;

    @Column(name = "document")
    String document;

}
