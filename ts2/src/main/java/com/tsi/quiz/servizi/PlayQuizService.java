package com.tsi.quiz.servizi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsi.quiz.datatransfer.AnswerResponse;
import com.tsi.quiz.datatransfer.GiudGlobalResponse;
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
public class PlayQuizService {
	private UserRepository userRepository;
	private QuizRepository quizRepository;
	private TipoQuizRepository tipoQuizRepository;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;
	private PlayAnswerRepository playAnswerRepository;
	private PlayQuizRepository playQuizRepository;

	public List<Answer> getAllAnswerByQuestion(String id) {

		Optional<Question> questionOptional = questionRepository.findById(id);

		Question question = questionOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));

		List<Answer> list;
		list = answerRepository.findByQuestion(question);
		List<String> verita = new ArrayList<String>();
		for (int counter = 0; counter < list.size(); counter++) {

			if (list.get(counter).isCorretta() == true) {
				verita.add("false");
			}
			;
		}
		if (verita.size() >= 2) {
			question.setRispostaMultipla(true);
		} else {
			question.setRispostaMultipla(false);
		}
		return answerRepository.findByQuestion(question);

	}

	public void editPlayAnswer(IdAnswerObj playAnswerId) {
		Optional<PlayAnswer> playQuizOptional = playAnswerRepository.findById(playAnswerId.getAnswerId());
		PlayAnswer playAnswer = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		
		Optional<PlayQuiz> plyquizO= playQuizRepository.findById(playAnswer.getPlayQuiz().getPlayQuizId());
		PlayQuiz plyquiz = plyquizO

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		if (!playAnswer.isCorretta()) {
		playAnswer.setCorretta(true);
		playAnswer.setPunteggio(playAnswer.getQuestion().getPunteggio());
		plyquiz.setPunteggioTecnico(plyquiz.getPunteggioTecnico()+playAnswer.getQuestion().getPunteggio());
		}
		else {
			playAnswer.setCorretta(false);
		playAnswer.setPunteggio((long) 0);
			plyquiz.setPunteggioTecnico(plyquiz.getPunteggioTecnico()-playAnswer.getQuestion().getPunteggio());
			
		}
		
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

	
	public void editgiudizioGLOBALByplayQuizId(@Valid GiudGlobalResponse giudglobal) {
		Optional<PlayQuiz> playQuizOptional = playQuizRepository.findById(giudglobal.getPlayQuizId());
		PlayQuiz playQuiz = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		playQuiz.setGiudizioGlobale(giudglobal.getGiudizionglobal());

		playQuizRepository.save(playQuiz);
		
	}
	
	
	public List<AnswerResponse> getplayAllAnswerByQuestion(String id) {

		Optional<Question> questionOptional = questionRepository.findById(id);

		Question question = questionOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		List<AnswerResponse> risposteResponse = new ArrayList<>();
		AnswerResponse a = new AnswerResponse();
		AnswerResponse b = new AnswerResponse();
		List<Answer> risposte = answerRepository.findByQuestion(question);
		for (int i = 0; i < risposte.size(); i++) {
			System.out.println(risposte.get(i));
			a.setAnswerId(risposte.get(i).getAnswerId());
			a.setAnswer(risposte.get(i).getAnswer());
			a.setQuestionId(risposte.get(i).getQuestion().getQuestionId());
			risposteResponse.add(new AnswerResponse(risposte.get(i).getAnswerId(), risposte.get(i).getAnswer(),
					risposte.get(i).getQuestion().getQuestionId()));

		}
		return risposteResponse;
	}

	public List<PlayAnswer> getplayAllAnswerByplayQuizId(String playQuizId) {
		Optional<PlayQuiz> playQuizOptional = playQuizRepository.findById(playQuizId);
		PlayQuiz playQuiz = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));

		List<PlayAnswer> allAnswer = playAnswerRepository.findAllByPlayQuiz(playQuiz);

//		}
		return allAnswer;
	}

	public void createPlayAnswer(PlayAnswerResponse playAnswerResponse) {
		PlayAnswer playAnswer = new PlayAnswer();
		playAnswer.setPlayAnswerId(playAnswerResponse.getAnswerId());
		playAnswer.setAnswer(playAnswerResponse.getAnswer());
		playAnswer.setTempo(playAnswerResponse.getTempo());
		System.out.print("	EDDAAAIIIIIIIIIIIIII");

		Optional<PlayQuiz> playQuizOp = playQuizRepository.findById(playAnswerResponse.getPlayQuizId());
		PlayQuiz playQuiz = playQuizOp
				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));
		playAnswer.setPlayQuiz(playQuiz);

		Optional<Question> questionOp = questionRepository.findById(playAnswerResponse.getQuestionId());
		Question question = questionOp
				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : "));

		List<Answer> risposte = getAllAnswerByQuestion(playAnswerResponse.getQuestionId());
		if (!question.isRispostaMultipla()) {
			String rispostaCoretta = "";

			for (int i = 0; i < risposte.size(); i++) {
				if (risposte.get(i).isCorretta()) {
					rispostaCoretta = risposte.get(i).getAnswer();
					break;
				}

			}

		

			Long nuovoPoint;
			System.out.print("porfavor");

			if (rispostaCoretta.equals(playAnswerResponse.getAnswer())&&playAnswerResponse.getTempo()>0) {
				playAnswer.setCorretta(true);

				System.out.print("	EDDAAAIIIIIIIIIIIIgggggggggggggI      " + playQuiz.getPunteggioTecnico());
				playAnswer.setPunteggio(question.getPunteggio());
				nuovoPoint = playQuiz.getPunteggioTecnico() + playAnswer.getPunteggio();
				playQuiz.setPunteggioTecnico(nuovoPoint);
				playQuizRepository.save(playQuiz);
			}

		}
		

		
		else if(question.isRispostaMultipla()) {
			Long nuovoPoint;
		String risposteCorrette = "";

		for (int i = 0; i < risposte.size(); i++) {
			if (risposte.get(i).isCorretta()) {
				risposteCorrette=	risposteCorrette.concat(risposte.get(i).getAnswer());
				System.out.print("	EDDAAAIIIIIIIIIIIIgggggggggggggI      " + playQuiz.getPunteggioTecnico());
			}

		}
	if(stesseRisposte(risposteCorrette,playAnswerResponse.getAnswer())&&playAnswerResponse.getTempo()>0 )
			 {
		playAnswer.setCorretta(true);
				playAnswer.setPunteggio(question.getPunteggio());
				nuovoPoint = playQuiz.getPunteggioTecnico() + playAnswer.getPunteggio();
				playQuiz.setPunteggioTecnico(nuovoPoint);
				playQuizRepository.save(playQuiz);

			}


		
		
		
		}

		playAnswer.setQuestion(question);
		playAnswerRepository.save(playAnswer);
	}
	
	
	
	private boolean stesseRisposte(String firstStr, String secondStr) {
		  char[] first = firstStr.toCharArray();
		  char[] second = secondStr.toCharArray();
		  Arrays.sort(first);
		  Arrays.sort(second);
		  return Arrays.equals(first, second);
		}

	

}
