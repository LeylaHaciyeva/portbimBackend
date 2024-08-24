package az.code.portbim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"az.code.portbim.model", "az.code.portbim.repository"})
public class PortbimApplication {
	public static void main(String[] args) {
		SpringApplication.run(PortbimApplication.class, args);
	}
}
