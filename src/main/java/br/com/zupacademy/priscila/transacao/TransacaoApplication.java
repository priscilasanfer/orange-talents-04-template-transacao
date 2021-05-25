package br.com.zupacademy.priscila.transacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import java.util.TimeZone;

@SpringBootApplication
public class TransacaoApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(TransacaoApplication.class, args);
	}

	@Bean
	JsonMessageConverter jsonMessageConverter(){
		return new JsonMessageConverter();
	}

}
