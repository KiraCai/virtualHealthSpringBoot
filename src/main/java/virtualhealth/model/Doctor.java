package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long idDoctor;

    // vaccination table
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Vaccination> vaccination;

    // prescription table
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Prescription> prescription;

    // test table
    @OneToMany(mappedBy = "doctor", cascade= CascadeType.ALL)
    @ToString.Exclude
    private List<Test> test;

    // consultation table
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Consultation> consultation;

    // calendar table
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Calendar> calendar;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "email_work")
    String emailWork;

    @Column(name = "email_personal")
    String emailPersonal;

    @Column(name = "password")
    String password;

    @Column(name = "education_secondary")
    String educationSecondary;

    @Column(name = "education_higher")
    String educationHigher;

    @Column(name = "experience")
    String experience;

    @Column(name = "tel_personal")
    Integer telPersonal;

    @Column(name = "tel_work")
    Integer telWork;

    @Column(name = "sex")
    Character sex;

    @Column(unique = true, name = "address_work")
    String addressWork;

    @Column(name = "address_personal")
    String addressPersonal;

    @Column(name = "name_establishment")
    String nameEstablishment;

    @Column(name = "job_title")
    String jobTitle;

    @Column(name = "document")
    String document;

    @Column(name = "photo")
    String photo;

    @Column(name = "about")
    String about;
}
