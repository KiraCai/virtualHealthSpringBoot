package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "biomarker")
public class Biomarker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "biomarker_id")
    private Long idBiomarker;

    //disease table
    @ManyToOne
    @JoinColumn(name = "disease_id")
    @ToString.Exclude
    private Disease disease;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
