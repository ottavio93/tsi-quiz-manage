package com.tsi.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsi.quiz.models.PlayAnswer;
import com.tsi.quiz.models.Question;
import com.tsi.quiz.models.TipoQuiz;




public interface QuestionRepository extends JpaRepository<Question, String> {
	List<Question> findByTipoQuiz(TipoQuiz tipoQuiz) ;
	Optional<Question> findById(String questionId);
	List<Question> removeByTipoQuiz(TipoQuiz tipoQuiz);
	
	
}
