package glass.symbiose.template.dto.response;

import glass.symbiose.template.port.JsonSerializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.StringWriter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaginationDto implements JsonSerializable {
    private int offset;
    private int limit;
    @JsonProperty("dataset_result_count")
    private int datasetResultCount;
    @JsonProperty("dataset_total")
    private int datasetTotal;
    @Override
    public String serializeJson() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("offset", offset);
        jsonGenerator.writeNumberField("limit", limit);
        jsonGenerator.writeNumberField("dataset_result_count", datasetResultCount);
        jsonGenerator.writeNumberField("dataset_total", datasetTotal);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
        return stringWriter.toString();
    }
}
