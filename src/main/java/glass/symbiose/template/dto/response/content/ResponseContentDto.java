package glass.symbiose.template.dto.response.content;

import glass.symbiose.template.port.JsonSerializable;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public abstract class ResponseContentDto implements JsonSerializable {
    public static String serializeJson(List<? extends ResponseContentDto> dtos){
        try {
            JsonFactory jsonFactory = new JsonFactory();
            StringWriter stringWriter = new StringWriter();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
            jsonGenerator.writeStartArray();
            for(ResponseContentDto dto : dtos){
                jsonGenerator.writeRawValue(dto.serializeJson());
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.close();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
