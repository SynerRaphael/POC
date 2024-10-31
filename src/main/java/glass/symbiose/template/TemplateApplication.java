package glass.symbiose.template;

import glass.symbiose.template.enumeration.ApiMessageLanguage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemplateApplication {
	public static final String serviceName = "TemplateApplication";
	public static ApiMessageLanguage apiMessageLanguage = ApiMessageLanguage.EN;
	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

}
