package com.tsi.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.tsi.quiz.servizi.QuizService;

@SpringBootApplication
@EnableAsync

public class SpringBootSecurityJwtApplication  {
	@Autowired
	QuizService quizService;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
		
	
	}

}
