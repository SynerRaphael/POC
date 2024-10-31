package glass.symbiose.template.exception;

public class UnmanagedException extends SymbioseException{
    public UnmanagedException(String message) {
        super(SymbioseExceptionId.UNMANAGED, message);
    }
}
