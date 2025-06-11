package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    private String type;                        // Тип фичи: VARIANT
    private String category;                   // Категория (например, MOLECULE_PROCESSING)
    private String cvId;                       // Controlled Vocabulary ID
    private String ftId;                       // Feature ID
    private String description;                // Описание мутации
    @JsonDeserialize(using = AlternativeSequenceDeserializer.class)
    private String alternativeSequence;        // Замещающая аминокислота
    private String begin;                      // Начало мутации
    private String end;                        // Конец мутации
    private String molecule;

    private Ligand ligand;
    private Ligand ligandPart;
    private List<Association> association;

    private List<Xref> xrefs;
    private List<ProteinEvidence> evidences;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Association {
    private String name;
    private String description;
    private Boolean disease;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class ProteinEvidence {
    private String code;
    @JsonDeserialize(using = SourceDeserializer.class)
    private Source source;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Source {
    private String id;
    private String name;
    private String url;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Ligand {
    private String name;
    private String label;
    private String note;
    private DBReference dbReference;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class DBReference {
    private String name;
    private String id;
    private String url;
    private String alternativeUrl;
    private Boolean reviewed;
    //private Map<String, Object> properties; // можно оставить пустым, если не используешь
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Xref {
    private String name;
    private String id;
    private String url;
    private String alternativeUrl;
    private Boolean reviewed;
    private Object properties; // если не используешь — можно удалить
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class EvidenceSource {
    private String name;
    private String id;
    private String url;
    private String alternativeUrl;
    private Boolean reviewed;
    private Object properties; // можно исключить
}


