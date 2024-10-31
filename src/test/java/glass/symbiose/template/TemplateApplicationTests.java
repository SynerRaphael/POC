package glass.symbiose.template;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootConfiguration
@EnableAutoConfiguration
public class TemplateApplicationTests {
    @Test
    void shouldX(){
        String expected = "Hello World !";
        String actual = "Hello World !";
        assertEquals(expected, actual);
    }
}
