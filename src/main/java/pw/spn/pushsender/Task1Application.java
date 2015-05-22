package pw.spn.pushsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class Task1Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Task1Application.class);
        app.setShowBanner(false);
        app.run(args);
    }

    @Bean
    ThreadPoolTaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
