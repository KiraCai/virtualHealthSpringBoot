package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "clients")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private Long id;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_history")
    private History history;*/

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

    @Column(name = "tel")
    String tel;

    @Column(name = "address")
    String address;
}
