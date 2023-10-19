package example.task.object_relationship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MainStart {
    public static void main(String[] args) {
        SpringApplication.run(MainStart.class);
    }
}
