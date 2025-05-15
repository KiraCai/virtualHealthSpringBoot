package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "client_id")
    private Long idClient;

    // calendar table
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Calendar> calendar;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "sex")
    Character sex;

    @Column(unique = true, name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "tel")
    String tel;

    @Column(name = "address")
    String address;

    @Column(name = "document")
    String document;

    @Column(name = "photo")
    String photo;

}
