package dolphin.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankProjectApplication.class, args);
	}
}
