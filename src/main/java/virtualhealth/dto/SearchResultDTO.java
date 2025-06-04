package virtualhealth.dto;
import lombok.Data;
import virtualhealth.model.Article;
import virtualhealth.model.ProteinInfo;

import java.util.List;

@Data
public class SearchResultDTO {
    private List<Article> articles;
    private List<ProteinInfo> proteins;

    public SearchResultDTO() {
        // обязательный пустой конструктор
    }

    public SearchResultDTO(List<Article> articles, List<ProteinInfo> proteins) {
        this.articles = articles;
        this.proteins = proteins;
    }
}
