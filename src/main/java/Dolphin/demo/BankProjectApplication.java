package Dolphin.demo;

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
