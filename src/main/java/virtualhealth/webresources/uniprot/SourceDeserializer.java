package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class SourceDeserializer extends JsonDeserializer<Source> {

    @Override
    public Source deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.isTextual()) {
            // Если пришла строка, типа "PubMed"
            return new Source(null, node.asText(), null);
        } else if (node.isObject()) {
            // Если пришёл объект
            String id = node.has("id") ? node.get("id").asText() : null;
            String name = node.has("name") ? node.get("name").asText() : null;
            String url = node.has("url") ? node.get("url").asText() : null;
            return new Source(id, name, url);
        } else {
            return null; // или выбросить исключение
        }
    }
}

