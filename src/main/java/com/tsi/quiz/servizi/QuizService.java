
package com.tsi.quiz.servizi;
					
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
	import java.util.Optional;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;

import com.tsi.quiz.datatransfer.AnswerRequest;
import com.tsi.quiz.datatransfer.PlayQuizResponse;
import com.tsi.quiz.datatransfer.QuestionRequest;
import com.tsi.quiz.datatransfer.QuizRequest;
import com.tsi.quiz.datatransfer.TipoQuizRequest;
import com.tsi.quiz.datatransfer.UserResponse;
import com.tsi.quiz.models.Answer;
import com.tsi.quiz.models.ERole;
import com.tsi.quiz.models.PlayAnswer;
import com.tsi.quiz.models.PlayQuiz;
import com.tsi.quiz.models.Question;
import com.tsi.quiz.models.Quiz;
import com.tsi.quiz.models.Role;
import com.tsi.quiz.models.TipoQuiz;
import com.tsi.quiz.models.User;
import com.tsi.quiz.repository.AnswerRepository;
import com.tsi.quiz.repository.PlayAnswerRepository;
import com.tsi.quiz.repository.PlayQuizRepository;
import com.tsi.quiz.repository.QuestionRepository;
import com.tsi.quiz.repository.QuizRepository;
import com.tsi.quiz.repository.RoleRepository;
import com.tsi.quiz.repository.TipoQuizRepository;
import com.tsi.quiz.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class QuizService {
	private UserRepository	userRepository;
	private QuizRepository quizRepository;
private TipoQuizRepository tipoQuizRepository;
	private QuestionRepository questionRepository;
 private AnswerRepository answerRepository;
    private PlayAnswerRepository playAnswerRepository;
    private PlayQuizRepository playQuizRepository;
    private RoleRepository  roleRepository;
    
	public void createPlayQuiz(PlayQuizResponse playQuizResponse) {
		PlayQuiz playQuiz=new PlayQuiz();
		
		playQuiz.setCognome(playQuizResponse.getCognome());
		playQuiz.setEmail(playQuizResponse.getEmail());
		playQuiz.setNome(playQuizResponse.getNome());
		playQuiz.setPlayQuizId(playQuizResponse.getPlayQuizId());
		playQuizRepository.save(playQuiz);
		}
//	public void createuserRole(UserResponse userRole) {
//		Role role=new Role();
//		
//		
//		role.setName(ERole.ROLE_USER);
//	
//		
//		roleRepository.save(role);
//		}
	@Transactional(readOnly = false)
	public void createQuiz(QuizRequest quizRequest) {
		Quiz quiz=new Quiz();
		quiz.setQuizId(quizRequest.getQuizId());
		quiz.setCreatedDate(Instant.now().toString());
		quiz.setNome(quizRequest.getNome());
				quiz.setDescription(quizRequest.getDescription());
	
quiz.setSeniority(quizRequest.getDifficolta());

		String userName=quizRequest.getUserName();

		Optional<User> userOptional =
				userRepository.findByUsername(userName); 
		User user = userOptional
				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " + userName));

		quiz.setUser(user);
		quizRepository.save(quiz);
	}

	
		
	
	public void createTipoQuiz(TipoQuizRequest tipoquizRequest) {
		TipoQuiz tipoQuiz=new TipoQuiz();

		tipoQuiz.setTipoQuizId(tipoquizRequest.getTipoQuizId());
		tipoQuiz.setArgomento(tipoquizRequest.getArgomento());


		Optional<Quiz> quizOptional = quizRepository.findById(tipoquizRequest.getIdQuiz());
		Quiz quiz = quizOptional
				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));

		tipoQuiz.setQuiz(quiz);
		tipoQuizRepository.save(tipoQuiz);
	}

	;
	public void createQuestion(QuestionRequest questionRequest) {
		Question question=new Question();
		question.setQuestionId(questionRequest.getQuestionId());
		question.setQuestion(questionRequest.getQuestion());
		question.setTempo(questionRequest.getTempo());
		question.setPunteggio(questionRequest.getPunteggio());

		Optional<TipoQuiz> tipoQuizOptional = tipoQuizRepository.findById(questionRequest.getTipoQuizId());
		TipoQuiz tipoQuiz = tipoQuizOptional
				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));

		question.setTipoQuiz(tipoQuiz);
		questionRepository.save(question);
	}
	


	@Transactional(readOnly = true)
	public List<TipoQuiz> geTipiQuizById(String
			id) { 

		Optional<Quiz> quizOptional = quizRepository.findById(id) ;


		Quiz quiz = quizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));

		return tipoQuizRepository.findByQuiz(quiz);
     }
	
	
	@Transactional(readOnly = true)
	public List<PlayQuiz> getAllTipiPlayQuiz() { 

return playQuizRepository.findAll();
     }
	
	
	
	
	
	public List<Question> getQuestionByTipiQuizId(String
			id) { 

		Optional<TipoQuiz> quizOptional = tipoQuizRepository.findById(id) ;


		TipoQuiz tipoquiz = quizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));

		return questionRepository.findByTipoQuiz(tipoquiz);



	}
	
	
	
	
	
	public void createAnswer(AnswerRequest answerRequest) {
		Answer answer=new Answer();
		answer.setAnswerId(answerRequest.getAnswerId());
		answer.setAnswer(answerRequest.getAnswer());
		answer.setCorretta(answerRequest.isCorretta());
		
		List<Answer> list;
		Optional<Question> questionOp= questionRepository.findById(answerRequest.getQuestionId());
		Question question = questionOp
				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));
		
		answer.setQuestion(question);
		answerRepository.save(answer);
		list=answerRepository.findByQuestion(question);
	}
	
	
	
	public List<Answer> getAllAnswerByQuestion(String
			id) { 

		Optional<Question> questionOptional = questionRepository.findById(id) ;


		Question question = questionOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));

		List<Answer> list;
		list=answerRepository.findByQuestion(question);
		List<String> verita =new ArrayList<String>(); 
		  for (int counter = 0; counter < list.size(); counter++) { 	
			  
	        if (   list.get(counter).isCorretta()==true) {
	        	verita.add("false");
	        }; 		
	      }   
		  if (verita.size()>=2) {
			  question.setRispostaMultipla(true);
		  }
		  else {
			  question.setRispostaMultipla(false);}
		return answerRepository.findByQuestion(question);


	}

	public Long getTotalPunteggioQuiz(String
			playQuizId) {
		Long totale=(long) 0;
	Optional<PlayQuiz> playQuizOptional = playQuizRepository.findById(playQuizId) ;
		PlayQuiz playQuiz = playQuizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));
	
		List<PlayAnswer> allAnswer = playAnswerRepository.findAllByPlayQuiz(playQuiz) ;
		for(int i=0;i < allAnswer.size(); i++) {
			totale+=allAnswer.get(i).getQuestion().getPunteggio();
	
		}
		
		return totale;
		
		
	}
	


	
	@Transactional(readOnly = true)
	public Quiz getQuizByNome(String
		id) { 

		Optional<Quiz> quizOptional = quizRepository.findByNome(id) ;


		Quiz quiz = quizOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " +
						"Found with username : " ));

		return quiz;
	}


	@Transactional(readOnly = true)
	public List<Quiz> getAllQuizByNome(String
		username) { 

		Optional<User> userOptional=userRepository.findByUsername(username);
		
		User user = userOptional

				.orElseThrow(() -> new UsernameNotFoundException("No user " +						"Found with username : " ));
		
	 List<Quiz>  quizUser = quizRepository.findByUser(user) ;


	
	return quizUser;
	}

	public List<Question> findAllplayByUser(String
			username){
			 List<Question> g = new ArrayList<Question>();
			 List<Question> p = new ArrayList<Question>();
			 List<PlayQuiz> play=playQuizRepository.findAll();
				Optional<User> userOptional=userRepository.findByUsername(username);
				
				User user = userOptional
		
						.orElseThrow(() -> new UsernameNotFoundException("No user " +
								"Found with username : " ));	
				 List<Quiz> listaQuiz= quizRepository.findByUser(user);
				 for(int i=0;i < listaQuiz.size(); i++) {
					 g.addAll(findAllQuestionByQuizId(listaQuiz.get(i).getQuizId()));		 
				}
				 
				 List<PlayQuiz>	d=	 playQuizRepository.findAll();
				 System.out.println(d.size() +"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//				 for(int i=0;i < d.size(); i++) {
//					 p=findAllQuestionByQuizId(d.get(i).getPlayQuizId());		 
//				}
				   System.out.println(g.size());
				 for(Question model : g) {
			            System.out.println(model.getQuestion());
			            System.out.println(g.size());
			        }

//			p.retainAll(g);
	        
				
			 return g;
		}
	
	
	public List<PlayAnswer> findAllPlayQuestionByUser(String
			username) { 
		List<PlayAnswer> allplay=new ArrayList<PlayAnswer>();
		List<Question> allq= findAllplayByUser(username);
		 for(Question model : allq) {
	            System.out.println(model.getQuestion());
	            allplay.addAll(playAnswerRepository.findAllByQuestion(model));      
	        }
		
		
		
		return  allplay;
	}
	
	
//	
	public List<PlayQuiz> findAllPlayQuizByUser(String
			username) { 
		List<PlayQuiz> allplayQuiz=new ArrayList<PlayQuiz>();
		List<PlayAnswer> allplay=findAllPlayQuestionByUser(username);
		System.out.println("fdgggggggghhhhhhhhhgggggggggggggggggggggggggggggggggggg");
		for(PlayAnswer model : allplay) {
            System.out.println(model.getPlayQuiz());
            allplayQuiz.add(playQuizRepository.findByPlayQuizId(model.getPlayQuiz().getPlayQuizId()).orElseThrow(() -> new UsernameNotFoundException("No user " +
					"Found with username : " ))	);      
        }
		
		
		  Set<PlayQuiz> listWithoutDuplicates = new LinkedHashSet<PlayQuiz>(allplayQuiz);
		  allplayQuiz.clear();

		  allplayQuiz.addAll(listWithoutDuplicates);
		
		
		
		
		
	return allplayQuiz;
	}
	
	
	
	
	
	
	
	
		public List<Question> findAllQuestionByQuizId(String
				id) { 
			Optional<Quiz> quizOptional = quizRepository.findById(id) ;
	        Quiz quiz = quizOptional
	          .orElseThrow(() -> new UsernameNotFoundException("No user " +
							"Found with username : " ));
	        List<TipoQuiz> tipiquiz = tipoQuizRepository.findByQuiz(quiz) ;
			List<Question> domande=  new ArrayList<Question>() ;
			 for (int i = 0; i < tipiquiz.size(); i++) {
				  System.out.println(tipiquiz.get(i));
		            domande.addAll(questionRepository.findByTipoQuiz(tipiquiz.get(i)));
		            
		        }
			 
			 
			 
			 
			return  domande;
		}
	
	

}
