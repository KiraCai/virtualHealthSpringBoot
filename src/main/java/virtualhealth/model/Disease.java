package virtualhealth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "disease_id")
    private Long idDisease;

    // biomarker table
    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Biomarker> biomarker;

    @Column(unique = true, name = "name")
    private String name; // Name of the disease

    @Column(name = "description")
    private String description; // Description of the disease

    @Column(name = "rate")
    private BigDecimal mortalityRate; // Уровень смертности

    @Column(name = "rank")
    private Integer worldRank; // World ranking

    @Column(name = "death")
    private Integer death; // Number of deaths

    @Column(name = "percentage")
    private BigDecimal percentage; // Mortality %
}
