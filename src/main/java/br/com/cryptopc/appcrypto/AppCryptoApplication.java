package br.com.cryptopc.appcrypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class AppCryptoApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppCryptoApplication.class, args);
	}
}
