package glass.symbiose.template.dto.response;

import glass.symbiose.template.port.JsonSerializable;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.*;

import java.io.IOException;
import java.io.StringWriter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ServiceErrorDto implements JsonSerializable {
    @NonNull
    private String service;
    @NonNull
    private String id;
    @NonNull
    private String message;

    @Override
    public String serializeJson() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("service", service);
        jsonGenerator.writeStringField("id", id);
        jsonGenerator.writeStringField("message", message);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
        return stringWriter.toString();
    }
}
