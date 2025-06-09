package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class ProteinDescription {
    private RecommendedName recommendedName;
    private List<AlternativeName> alternativeNames;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class RecommendedName {
    private FullName fullName;
    private List<ShortName> shortNames;
    private List<Evidence> evidences;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class AlternativeName {
    private FullName fullName;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class FullName {
    private String value;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class ShortName {
    private String value;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Comment {
    private String commentType;
    //private List<Text> texts;
    private Disease disease;
    private DbReference dbReference; //добавила для поиска  PDB ID
    //private List<Interaction> interactions;
}



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Interaction {
    private String interactantOne;
    private String interactantTwo;
    private List<Evidence> evidences;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class DiseaseComment extends Comment {
    private Disease disease;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class StructureComment extends Comment {
    private DbReference dbReference;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class TextualComment extends Comment {
    private List<Text> texts;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class InteractionComment extends Comment {
    private List<Interaction> interactions;
}


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Text {
    private String value;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Disease {
    private String diseaseId;
    private String diseaseAccession;
    private String acronym;
    private String description;
    private DiseaseCrossReference diseaseCrossReference;
    private List<Evidence> evidences;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class DiseaseCrossReference {
    private String database;
    private String id;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class Evidence {
    private String evidenceCode;
    private String source;
    private String id;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
class DbReference {
    private String type;
    private String id;
}

