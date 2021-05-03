package com.tsi.quiz.servizi;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsi.quiz.datatransfer.AnswerResponse;
import com.tsi.quiz.datatransfer.GiudPsiResponse;
import com.tsi.quiz.datatransfer.IdAnswerObj;
import com.tsi.quiz.datatransfer.PlayAnswerResponse;
import com.tsi.quiz.models.Answer;
import com.tsi.quiz.models.PlayAnswer;
import com.tsi.quiz.models.PlayQuiz;
import com.tsi.quiz.models.Question;
import com.tsi.quiz.repository.AnswerRepository;
import com.tsi.quiz.repository.PlayAnswerRepository;
import com.tsi.quiz.repository.PlayQuizRepository;
import com.tsi.quiz.repository.QuestionRepository;
import com.tsi.quiz.repository.QuizRepository;
import com.tsi.quiz.repository.TipoQuizRepository;
import com.tsi.quiz.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ReportService {
	private UserRepository userRepository;
	private QuizRepository quizRepository;
	private TipoQuizRepository tipoQuizRepository;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;
	private PlayAnswerRepository playAnswerRepository;
	private PlayQuizRepository playQuizRepository;



	public void editPlayAnswer(IdAnswerObj playAnswerId) {
		Optional<PlayAnswer> playQuizOptional = playAnswerRepository.findById(playAnswerId.getAnswerId());
		PlayAnswer playAnswer = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		
		Optional<PlayQuiz> plyquizO= playQuizRepository.findById(playAnswer.getPlayQuiz().getPlayQuizId());
		PlayQuiz plyquiz = plyquizO

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		
		playAnswer.setCorretta(true);
		playAnswer.setPunteggio(playAnswer.getQuestion().getPunteggio());
		plyquiz.setPunteggioTecnico(plyquiz.getPunteggioTecnico()+playAnswer.getQuestion().getPunteggio());
		
		
		playQuizRepository.save(plyquiz);
		playAnswerRepository.save(playAnswer);
	}

	public void editgiudizioPsyByplayQuizId(GiudPsiResponse giudPsi) {
		Optional<PlayQuiz> playQuizOptional = playQuizRepository.findById(giudPsi.getPlayQuizId());
		PlayQuiz playQuiz = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		playQuiz.setGiudizioPsicologico(giudPsi.getGiudizionpsi());

		playQuizRepository.save(playQuiz);
	}







	
	public void editApporvato(String playQuizId) {
		Optional<PlayQuiz> playQuizOptional = playQuizRepository.findById(playQuizId);
		PlayQuiz playQuiz = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));

	playQuiz.setApprovato(true);
	playQuizRepository.save(playQuiz);
	}

		




	
	


}
