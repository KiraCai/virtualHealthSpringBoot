package virtualhealth.webresources.pubmed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDetails {


    @JacksonXmlProperty(localName = "ArticleTitle")
    private String title;

    @JacksonXmlProperty(localName = "Abstract")
    private AbstractSection abstractSection;

    @JacksonXmlProperty(localName = "AuthorList")
    private AuthorList authorList;

    @JacksonXmlProperty(localName = "Journal")
    private Journal journal;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class AuthorList {
    @JacksonXmlProperty(localName = "Author")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Author> authors;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Author {
    @JacksonXmlProperty(localName = "LastName")
    private String lastName;

    @JacksonXmlProperty(localName = "ForeName")
    private String foreName;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Journal {
    @JacksonXmlProperty(localName = "Title")
    private String title;
}



