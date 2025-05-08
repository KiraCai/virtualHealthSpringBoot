package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long idHistory;

    //client table
    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    Client client;

    //vaccination table
    @OneToMany(mappedBy = "history", cascade= CascadeType.ALL)
    private List<Vaccination> vaccination;

    //test table
    @OneToMany(mappedBy = "history", cascade= CascadeType.ALL)
    private List<Test> test;

    //prescription table
    @OneToMany(mappedBy = "history", cascade= CascadeType.ALL)
    private List<Prescription> prescription;

    //consultation table
    @OneToMany(mappedBy = "history", cascade= CascadeType.ALL)
    private List<Consultation> consultation;

}
