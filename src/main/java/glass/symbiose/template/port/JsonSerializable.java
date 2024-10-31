package glass.symbiose.template.port;

import java.io.IOException;

public interface JsonSerializable {
    String serializeJson() throws IOException;
}
