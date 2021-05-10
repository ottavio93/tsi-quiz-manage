package com.tsi.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsi.quiz.models.PlayAnswer;
import com.tsi.quiz.models.PlayQuiz;
import com.tsi.quiz.models.Question;



public interface PlayAnswerRepository extends JpaRepository<PlayAnswer, String> {
	
	List<PlayAnswer> removeByQuestion(Question question);

	List<PlayAnswer> findAllByPlayQuiz(PlayQuiz playQuiz);
	List<PlayAnswer> findAllByQuestion(Question question);
}