package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long idBooking;

    // doctor table
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // client table
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "meeting_place")
    String meetingPlace;

    @Column(name = "reason")
    String reason;
}
