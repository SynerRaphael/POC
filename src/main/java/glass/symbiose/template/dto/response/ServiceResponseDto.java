package glass.symbiose.template.dto.response;

import glass.symbiose.template.TemplateApplication;
import glass.symbiose.template.enumeration.ServiceResponseStatus;
import glass.symbiose.template.exception.SymbioseException;
import glass.symbiose.template.port.JsonSerializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.*;

import java.io.IOException;
import java.io.StringWriter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ServiceResponseDto implements JsonSerializable {
    @NonNull
    private ServiceResponseStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaginationDto pagination;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonRawValue
    private Object content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ServiceErrorDto error;
    public ServiceResponseDto(SymbioseException exception) {
        this.status = ServiceResponseStatus.FAILURE;
        this.error = ServiceErrorDto.builder()
                .id(String.valueOf(exception.getId()))
                .message(exception.getMessage())
                .service(TemplateApplication.serviceName)
                .build();
    }

    @Override
    public String serializeJson() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("status", String.valueOf(status));
        if (pagination != null){
            jsonGenerator.writeFieldName("pagination");
            jsonGenerator.writeRawValue(pagination.serializeJson());
        }
        if (content != null){
            jsonGenerator.writeFieldName("content");
            jsonGenerator.writeRawValue((String) content);
        }
        if (error != null){
            jsonGenerator.writeFieldName("error");
            jsonGenerator.writeRawValue(error.serializeJson());
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
        return stringWriter.toString();
    }
}
