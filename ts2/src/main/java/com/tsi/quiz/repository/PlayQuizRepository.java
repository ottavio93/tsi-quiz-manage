package com.tsi.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsi.quiz.models.PlayAnswer;
import com.tsi.quiz.models.PlayQuiz;
import com.tsi.quiz.models.Quiz;
import com.tsi.quiz.models.User;



public interface PlayQuizRepository extends JpaRepository <PlayQuiz,String>{


	Optional<PlayQuiz>findByPlayQuizId(String playQuiz);
	
}