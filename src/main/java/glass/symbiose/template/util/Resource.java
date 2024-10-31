package glass.symbiose.template.util;

import java.io.IOException;
import java.io.InputStream;

public abstract class Resource {
    public static String getJsonFileAsStringFromResources(String jsonFilePathName, Class<?> className) throws IOException {
        ClassLoader classLoader = className.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(jsonFilePathName);
        assert inputStream != null;
        return new String(inputStream.readAllBytes());
    }
}
