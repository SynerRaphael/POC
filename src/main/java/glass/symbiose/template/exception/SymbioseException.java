package glass.symbiose.template.exception;

import glass.symbiose.template.TemplateApplication;
import glass.symbiose.template.enumeration.ApiMessageLanguage;
import glass.symbiose.template.util.Resource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Getter
public abstract class SymbioseException extends Exception {
    protected final SymbioseExceptionId id;
    private static final Map<SymbioseExceptionId, Map<ApiMessageLanguage, String>> symbioseExceptionMessageTranslations;
    public SymbioseException(SymbioseExceptionId id, String message){
        super(message);
        this.id = id;
    }
    public static String getExceptionMessage(SymbioseExceptionId id){
        ApiMessageLanguage language = ApiMessageLanguage.EN;
        Map<ApiMessageLanguage, String> exceptionIdMessageTranslations = symbioseExceptionMessageTranslations.get(id);
        if (    exceptionIdMessageTranslations.containsKey(TemplateApplication.apiMessageLanguage) &&
                !exceptionIdMessageTranslations.get(TemplateApplication.apiMessageLanguage).isEmpty()){
            language = TemplateApplication.apiMessageLanguage;
        }
        return String.format("[%s|%s] %s", TemplateApplication.serviceName, id, exceptionIdMessageTranslations.get(language));
    }
    static {
        try {
            String symbioseExceptionMessageTranslationsAsJson =
                    Resource.getJsonFileAsStringFromResources(
                            "translation/symbioseExceptionMessageTranslations.json",
                            SymbioseException.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootJsonNode = objectMapper.readTree(symbioseExceptionMessageTranslationsAsJson);
            symbioseExceptionMessageTranslations = getSymbioseExceptionMessageTranslations(rootJsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Map<ApiMessageLanguage, String> getSymbioseExceptionIdExceptionMessageTranslations(JsonNode symbioseExceptionIdJsonNode){
        Iterator<Map.Entry<String, JsonNode>> languages = symbioseExceptionIdJsonNode.fields();
        Map<ApiMessageLanguage, String> symbioseExceptionIdExceptionMessageTranslations = new HashMap<>();
        while (languages.hasNext()) {
            Map.Entry<String, JsonNode> currentLanguage = languages.next();
            symbioseExceptionIdExceptionMessageTranslations.put(
                    ApiMessageLanguage.valueOf(currentLanguage.getKey()),
                    currentLanguage.getValue().textValue());
        }
        return symbioseExceptionIdExceptionMessageTranslations;
    }
    private static Map<SymbioseExceptionId, Map<ApiMessageLanguage, String>> getSymbioseExceptionMessageTranslations(JsonNode rootJsonNode){
        Map<SymbioseExceptionId, Map<ApiMessageLanguage, String>> symbioseExceptionMessageTranslations = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> symbioseExceptionIds = rootJsonNode.fields();
        while (symbioseExceptionIds.hasNext()) {
            Map.Entry<String, JsonNode> currentSymbioseExceptionId = symbioseExceptionIds.next();
            symbioseExceptionMessageTranslations.put(
                    SymbioseExceptionId.valueOf(currentSymbioseExceptionId.getKey()),
                    getSymbioseExceptionIdExceptionMessageTranslations(currentSymbioseExceptionId.getValue()));
        }
        return symbioseExceptionMessageTranslations;
    }
}
