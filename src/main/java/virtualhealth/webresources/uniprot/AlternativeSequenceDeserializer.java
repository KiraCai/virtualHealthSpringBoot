package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class AlternativeSequenceDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonToken token = parser.currentToken();

        if (token == JsonToken.VALUE_STRING) {
            return parser.getText();
        } else if (token == JsonToken.START_OBJECT) {
            // пропускаем вложенный объект и возвращаем как null или "[object]" — на выбор
            parser.skipChildren();
            return null; // или return "[object]" если нужно отображать
        } else {
            return null;
        }
    }
}

