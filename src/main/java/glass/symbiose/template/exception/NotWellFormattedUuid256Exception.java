package glass.symbiose.template.exception;

public class NotWellFormattedUuid256Exception extends SymbioseException {
    public NotWellFormattedUuid256Exception(String uuid256AsString) {
        super(SymbioseExceptionId.NOT_WELL_FORMATTED_UUID, String.format(getExceptionMessage(SymbioseExceptionId.NOT_WELL_FORMATTED_UUID), uuid256AsString));
    }
    public NotWellFormattedUuid256Exception(String UuidDataName, String uuid256AsString) {
        super(SymbioseExceptionId.NOT_WELL_FORMATTED_UUID_NAMED_DATA, String.format(getExceptionMessage(SymbioseExceptionId.NOT_WELL_FORMATTED_UUID_NAMED_DATA), uuid256AsString, UuidDataName));
    }
}
