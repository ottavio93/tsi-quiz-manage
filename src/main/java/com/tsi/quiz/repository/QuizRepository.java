package com.tsi.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsi.quiz.models.Quiz;
import com.tsi.quiz.models.User;



public interface QuizRepository extends JpaRepository<Quiz, String> {

	
	 List<Quiz> findByUser(User user); 
	
//	 List<Question> findByQuestion_tipoQuizs_QuizId(String quizId);
//	 Optional<User> findUserById(long id);
	 Optional<Quiz> findById(String quizId);
	 Optional<Quiz> findByNome(String nome);
}
