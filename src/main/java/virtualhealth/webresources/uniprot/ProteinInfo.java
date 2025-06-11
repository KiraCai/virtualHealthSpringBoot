package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProteinInfo {
    private String entryType;
    private String primaryAccession;  //Основной идентификатор белка
    private String uniProtkbId;  // 	ID записи UniProt
    @ToString.Exclude
    private List<String> secondaryAccessions; //Список альтернативных ID
    private Double annotationScore; //Показывает, насколько хорошо белок описан. 5 — максимум, много проверенных данных.
    private Organism organism; //Вид (например, Homo sapiens)
    private String proteinExistence; //Уровень доказательств существования белк
    private ProteinDescription proteinDescription; //Название белка альтернативное
    @ToString.Exclude
    private List<Gene> genes; // список генов
    @ToString.Exclude
    private List<Comment> comments;
    @ToString.Exclude
    private List<UniProtKBCrossReference> uniProtKBCrossReferences;
    private String commentType;
    private String properties;
    private Sequence sequence;
    @ToString.Exclude
    private List<Feature> features;


}
