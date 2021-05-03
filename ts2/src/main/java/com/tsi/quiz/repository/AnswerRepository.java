package com.tsi.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsi.quiz.models.Answer;
import com.tsi.quiz.models.Question;


public interface AnswerRepository extends JpaRepository <Answer,String>{

	List<Answer> findByQuestion(Question question);
	List<Answer> removeByQuestion(Question question);
	void removeByAnswer(String answerId);
	Optional<Answer> findById(String answerId);
}
