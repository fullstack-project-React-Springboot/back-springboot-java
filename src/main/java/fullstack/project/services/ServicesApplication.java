package fullstack.project.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fullstack.project")
public class ServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicesApplication.class, args);
    }

}
