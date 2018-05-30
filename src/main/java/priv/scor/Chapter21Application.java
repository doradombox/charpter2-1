package priv.scor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Chapter21Application{

	public static void main(String[] args) {
		SpringApplication.run(Chapter21Application.class, args);
	}
}
