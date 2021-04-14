package com.tsi.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsi.quiz.models.Quiz;
import com.tsi.quiz.models.TipoQuiz;



public interface TipoQuizRepository extends JpaRepository<TipoQuiz, String>{
	 
 List<TipoQuiz> findByQuiz(Quiz quiz) ;
 Optional<TipoQuiz> findById(String quizTipoId);

}
