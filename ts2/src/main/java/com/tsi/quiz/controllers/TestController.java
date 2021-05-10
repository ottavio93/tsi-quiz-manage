package com.tsi.quiz.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsi.quiz.datatransfer.AnswerDelete;
import com.tsi.quiz.datatransfer.AnswerRequest;
import com.tsi.quiz.datatransfer.AnswerResponse;
import com.tsi.quiz.datatransfer.GiudGlobalResponse;
import com.tsi.quiz.datatransfer.GiudPsiResponse;
import com.tsi.quiz.datatransfer.IdAnswerObj;
import com.tsi.quiz.datatransfer.PlayAnswerResponse;
import com.tsi.quiz.datatransfer.PlayQuizResponse;
import com.tsi.quiz.datatransfer.QuestionDelete;
import com.tsi.quiz.datatransfer.QuestionRequest;
import com.tsi.quiz.datatransfer.QuizDelete;
import com.tsi.quiz.datatransfer.QuizRequest;
import com.tsi.quiz.datatransfer.TipoQuizDelete;
import com.tsi.quiz.datatransfer.TipoQuizRequest;
import com.tsi.quiz.datatransfer.TipoQuizUpdate;
import com.tsi.quiz.datatransfer.UserResponse;
import com.tsi.quiz.models.Answer;
import com.tsi.quiz.models.PlayAnswer;
import com.tsi.quiz.models.PlayQuiz;
import com.tsi.quiz.models.Question;
import com.tsi.quiz.models.Quiz;
import com.tsi.quiz.models.TipoQuiz;
import com.tsi.quiz.models.User;
import com.tsi.quiz.repository.AnswerRepository;
import com.tsi.quiz.repository.PlayAnswerRepository;
import com.tsi.quiz.repository.QuestionRepository;
import com.tsi.quiz.repository.QuizRepository;
import com.tsi.quiz.repository.TipoQuizRepository;
import com.tsi.quiz.repository.UserRepository;
import com.tsi.quiz.servizi.PlayQuizService;
import com.tsi.quiz.servizi.QuizService;
import com.tsi.quiz.servizi.ReportService;

import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	QuizService quizService;
	@Autowired
	PlayQuizService playQuizService;

	 @Autowired
	 private QuizRepository quizRepository;
	 @Autowired
	 private TipoQuizRepository tipoQuizRepository;
	 @Autowired
	 private AnswerRepository  answerRepository;
	 @Autowired
	 private QuestionRepository questionRepository;
	 @Autowired
	 private PlayAnswerRepository playAnswerRepository;
	 @Autowired
	 private UserRepository userRepository;
	 @Autowired
	 private ReportService  reportService;
	
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	  @Transactional
	    @PostMapping("/editGiudiPsi")
	    public ResponseEntity<String> editgiudizioPsyByplayQuizId(@Valid @RequestBody GiudPsiResponse giudPsi){
	    	playQuizService.editgiudizioPsyByplayQuizId(giudPsi);
	    	 return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	  @Transactional
	    @PostMapping("/editGiudiGlobal")
	    public ResponseEntity<String> editgiudizioGlobalByplayQuizId(@Valid @RequestBody GiudGlobalResponse giudglobal){
	    	playQuizService.editgiudizioGLOBALByplayQuizId(giudglobal);
	    	 return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	

	   @PostMapping("/role")
	    
	    public ResponseEntity<Void> createrole( ) {
	    	System.out.print("noooooooooooooo");
	    	quizService.createuserRole();
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   @PostMapping("/role2")
	    
	    public ResponseEntity<Void> createrole2( ) {
	    	System.out.print("noooooooooooooo");
	    	quizService.createuserRole2();
	    	System.out.print("noooooooooogomkmooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   @PostMapping("/role3")
	    
	    public ResponseEntity<Void> createrole3( ) {
	    	System.out.print("nooooooofooooooo");
	    	quizService.createuserRole3();
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @PostMapping("createQuiz")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Void> createQuiz( @Valid @RequestBody QuizRequest quiz) {
	    	System.out.print("noooooooooooooo");
	    	quizService.createQuiz(quiz);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    @PostMapping("createArgomentoQuiz")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Void> createArgomentoQuiz( @Valid @RequestBody TipoQuizRequest tipoquiz) {
	    	System.out.print("siiiiii");
	    	quizService.createTipoQuiz(tipoquiz);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @PostMapping("createQuestion")
//	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> createQuestion( @Valid @RequestBody QuestionRequest questionRequest) {
	    	System.out.print("noooooooooooooo");
	    	quizService.createQuestion(questionRequest);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @PostMapping("createAnswer")
//	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> createAnswer( @Valid @RequestBody AnswerRequest answerRequest) {
	    	System.out.print("noooooooooooooo");
	    	quizService.createAnswer(answerRequest);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    
	    @PostMapping("createPlayAnswer")
//	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> createPLayAnswer( @Valid @RequestBody PlayAnswerResponse PlayAnswerResponse) {
	    	System.out.print("noooooooooooooo");
	    	playQuizService.createPlayAnswer(PlayAnswerResponse);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    
	    
	    
	    
	    @PostMapping("createPlayQuiz")
//	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> createPlayQuiz(@Valid @RequestBody  PlayQuizResponse playQuizResponse){
	    	quizService.createPlayQuiz(playQuizResponse);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    
	    
	    @PostMapping("/editPlayAnswer")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")

	public ResponseEntity<String> editPlayAnswer(@Valid @RequestBody IdAnswerObj playAnswerId){
	    	System.out.print("noooooooooooooooooooooooooo");
	    	playQuizService.editPlayAnswer(playAnswerId);
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    @PutMapping("editTipoQuiz/{tipoQuizId}")
      public ResponseEntity<String> updateTipoQuizName(@Valid @RequestBody TipoQuizUpdate tipoQuizUpdate,@PathVariable("tipoQuizId")String tipoQuizId){
	    	System.out.print("noooooooooooooooooooooooooo");
	    	
	    	TipoQuiz tipoQuiz = tipoQuizRepository.getOne(tipoQuizId);
	    	tipoQuiz.setArgomento(tipoQuizUpdate.getArgomento());
	    	tipoQuizRepository.save(tipoQuiz);
	    	
	    	System.out.print("noooooooooooooooooooooooooo");

	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    @PutMapping("editApprovato/{playQuizId}")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")

	      public void editApprovato(@PathVariable("playQuizId")String playQuizId){
		    	System.out.print("noooooooooooooooooooooooooo");
		    	
		    	reportService.editApporvato(playQuizId);
		    	
		    	System.out.print("fatto");

		      
		    }
	    
	    
	  
	    
	    
	    
	    
	    
	    @GetMapping("/allQuestion/{tipoQuizId}")
	    public ResponseEntity<List<Question>> getallQuestion( @PathVariable("tipoQuizId")String tipoQuizId) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<Question> t=quizService.getQuestionByTipiQuizId(tipoQuizId);
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    
	    
	    
	    
	    @GetMapping("/user/{username}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<List<Quiz>> userAccess(@PathVariable("username")String username) {
	    	System.out.print("lllllllllknnnnnnnnnnnnnnn");
	    	List<Quiz> lq=	quizService.getAllQuizByNome(username);
			return ResponseEntity.status(OK).body(lq);
		}

	    
	    
	    @GetMapping("/allQuiz")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<List<Quiz>> getAllTipiQuiz( ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<Quiz> t=quizRepository.findAll();
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    @GetMapping("/allUser")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<List<User>> getAllusers( ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<User> t=userRepository.findAll();
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    
	    @GetMapping("/QuizById/{idQuiz}")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Quiz> getQuizById(@PathVariable("idQuiz")String idQuiz ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 Optional<Quiz>  t=quizRepository.findById(idQuiz);
		 Quiz quiz = t

					.orElseThrow(() -> new UsernameNotFoundException("No user " +
							"Found with username : " ));
	        return ResponseEntity.status(OK).body(quiz);
	    }
	    
	    @GetMapping("/TipiDelQuiz/{idQuiz}")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<List<TipoQuiz>> geTipiQuizById(@PathVariable("idQuiz")String idQuiz ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<TipoQuiz> t=quizService.geTipiQuizById(idQuiz);
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    @GetMapping("/TipoQuiz/{idtipoQuiz}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<TipoQuiz> getTipoQuizById(@PathVariable("idtipoQuiz") String idtipoQuiz ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 Optional<TipoQuiz> t=tipoQuizRepository.findById(idtipoQuiz);
		 TipoQuiz tipoquiz = t

					.orElseThrow(() -> new UsernameNotFoundException("No user " +
							"Found with username : " ));

	        return ResponseEntity.status(OK).body(tipoquiz);
	    }
	    
	    @GetMapping("/question/{idQuestion}")
	
	    public ResponseEntity<Question> getQuestionById(@PathVariable("idQuestion") String idQuestion ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 Optional<Question> t=questionRepository.findById(idQuestion);
		 Question question = t

					.orElseThrow(() -> new UsernameNotFoundException("No user " +
							"Found with username : " ));

	        return ResponseEntity.status(OK).body(question);
	    } 
	    
	    
	    @GetMapping("/allQuestionByQuiz/{idQuiz}")
	    
	    public ResponseEntity<List<Question>> getAllQuestionByQuiz(@PathVariable("idQuiz")String idQuiz ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<Question> t=quizService.findAllQuestionByQuizId(idQuiz);
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    @GetMapping("/allanswer/{idQuestion}")
	    public ResponseEntity<List<Answer>> getAnswerById(@PathVariable("idQuestion")String idQuestion ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<Answer> t=quizService.getAllAnswerByQuestion(idQuestion);
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    
	    @GetMapping("/playallanswer/{idQuestion}")
	    public ResponseEntity<List<AnswerResponse>> getplayAnswerById(@PathVariable("idQuestion")String idQuestion ) {
		 System.out.print("lllllllllknnnnnnnnnnnnnnn");	
		 List<AnswerResponse> t=playQuizService.getplayAllAnswerByQuestion(idQuestion);
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    
	    
	    
	    @GetMapping("/getplayAnswerByQuizId/{idPLayQuiz}")
	    public ResponseEntity<List<PlayAnswer>> getplayAnswerByQuizId(@PathVariable("idPLayQuiz")String idPLayQuiz ) {
		
		 List<PlayAnswer> t=playQuizService.getplayAllAnswerByplayQuizId(idPLayQuiz);
	    	
	        return ResponseEntity.status(OK).body(t);
	    }
	    
	  
	    @GetMapping("/TipiDelQuiz2/{nomeQuiz}")
	    public Quiz getQuizByNome(@PathVariable("nomeQuiz")String
	    		nomeQuiz) {
	    	Quiz q=quizService.getQuizByNome(nomeQuiz);
	    	return q;
	    }
	  
	    @GetMapping("/userplay/{nomeu}")
	    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	    public List<PlayQuiz> findAllPlayQuestionByUser(@PathVariable("nomeu")String
	    		nomeu) {
	    	List<PlayQuiz> q=quizService.findAllPlayQuizByUser(nomeu);
	    	return q;
	    }
	  
	    
	    @GetMapping("/allReports")
	    @PreAuthorize("  hasRole('ADMIN')")
	    public ResponseEntity<List<PlayQuiz>>  getAllTipiPlayQuiz() {
			 List<PlayQuiz> t=quizService.getAllTipiPlayQuiz();
	    	return ResponseEntity.status(OK).body(t);
	    }
	    
	    
	    @GetMapping("/punteggioTotale/{playQuizId}")
	    public ResponseEntity <Long> getTotalPunteggioQuiz(@PathVariable("playQuizId") String
	    		playQuizId) {
			Long t=quizService.getTotalPunteggioQuiz(playQuizId);
	    	return ResponseEntity.status(OK).body(t);
	    }
	    
	    
	    
	    @Transactional
	    @PostMapping("/deleteQuestion")
	    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Void> deleteQuestion( @RequestBody QuestionDelete questionDelete) {
	    	System.out.print("noooooooooooooo");
//	    	auth = SecurityContextHolder.getContext().getAuthentication();
//	    	
//	    	String username = auth.getName();
//	    	System.out.print("allora che si fan             "+username);
//	    	if (username.equals(postDelete.getUserName())) {
	    	Optional<Question> optionalquestion=questionRepository.findById(questionDelete.getQuestionId());
	     	System.out.println("fatto");
	    	Question question = optionalquestion
	    			.orElseThrow(() -> new UsernameNotFoundException("No question " +
							"Found with username : " ));
	       	System.out.print("noooooooofffddddddddddddddddddddoooooo");
	    	answerRepository.removeByQuestion(question);
	    	playAnswerRepository.removeByQuestion(question);
	    questionRepository.deleteById(questionDelete.getQuestionId());
	    	System.out.print("cancellato");

	        
	    	
	    	return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @Transactional
	    @PostMapping("/deleteQuestion2")
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Void> deleteQuestion2() {
	    	System.out.print("noooooooooooooo");
//	    	auth = SecurityContextHolder.getContext().getAuthentication();
//	    	
//	    	String username = auth.getName();
//	    	System.out.print("allora che si fan             "+username);
//	    	if (username.equals(postDelete.getUserName())) {
	    	Optional<Question> optionalquestion=questionRepository.findById("htgfmnib");
	     	System.out.println("fatto");
	    	Question question = optionalquestion
	    			.orElseThrow(() -> new UsernameNotFoundException("No question " +
							"Found with username : " ));
	       	System.out.print("noooooooofffddddddddddddddddddddoooooo");
	    	answerRepository.removeByQuestion(question);
	    	playAnswerRepository.removeByQuestion(question);
	    questionRepository.deleteById("htgfmnib");
	    	System.out.print("cancellato");

	        
	    	
	    	return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    @Transactional
	    @PostMapping("answerDelete")
	    public ResponseEntity<Void> deleteAswer( @RequestBody AnswerDelete answerDelete) {
	    	System.out.print("noooooooooooooo");

	    	answerRepository.deleteById(answerDelete.getAnswerId());
	    	
	       
	    	playAnswerRepository.deleteById(answerDelete.getAnswerId());
	   
	    	System.out.print("cancellato");

	        
	    	
	    	return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @Transactional
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    @PostMapping("tipoquizDelete")
	    public ResponseEntity<Void> tipoquizDelete( @RequestBody TipoQuizDelete tipoquizDelete) {
	    	System.out.print("noooooooooooooo");
//	    	auth = SecurityContextHolder.getContext().getAuthentication();
//	    	
//	    	String username = auth.getName();
//	    	System.out.print("allora che si fan             "+username);
//	    	if (username.equals(postDelete.getUserName())) {
	    	Optional<TipoQuiz> optionalquestion=tipoQuizRepository.findById(tipoquizDelete.getTipoQuizId());
	     	System.out.println("fatto");
	     	TipoQuiz tipoQuiz = optionalquestion
	    			.orElseThrow(() -> new UsernameNotFoundException("No tipoQuiz " +
							"Found with tipoQuizId : " ));
	       	System.out.print("noooooooofffddddddddddddddddddddoooooo");
	       	
	       	List<Question> lq=   questionRepository.findByTipoQuiz(tipoQuiz);
	        for (Question q:lq) {
		    	answerRepository.removeByQuestion(q);
		      }
	       	
	       	questionRepository.removeByTipoQuiz(tipoQuiz);
	     
	       
//	    	playAnswerRepository.removeByAnswer(answerDelete.getAnswerId());
	   
	    	System.out.print("cancellato");
	    	tipoQuizRepository.deleteById(tipoquizDelete.getTipoQuizId());
	        
	    	
	    	return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @Transactional
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    @PostMapping("quizDelete")
	    public ResponseEntity<Void> quizDelete( @RequestBody QuizDelete quizDelete) {
	    	System.out.print("noooooooooooooo");
	    	
	    	Optional<Quiz> optionalquiz=quizRepository.findById(quizDelete.getQuizId());
	    	Quiz quiz = optionalquiz
	    			.orElseThrow(() -> new UsernameNotFoundException("No tipoQuiz " +
							"Found with tipoQuizId : " ));
	    	System.out.print("nooooooooofgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggooooo");
	     	List<TipoQuiz> tipiQuiz=   tipoQuizRepository.findByQuiz(quiz);
	    	
	     	for (TipoQuiz q:tipiQuiz) {
	     		TipoQuizDelete tipoQuizDelete=new TipoQuizDelete(q.getTipoQuizId());
	     		
	     		tipoquizDelete(tipoQuizDelete);
	    
	     	System.out.println("fatto");
	     	}
	     
	    quizRepository.deleteById(quizDelete.getQuizId());
	     return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    @Transactional
	    
	    @PostMapping("quizDelete2")
	    public ResponseEntity<Void> quizDelete2( ) {
	    
	    	
	    	Optional<Quiz> optionalquiz=quizRepository.findById("cu78ypgm");
	    	Quiz quiz = optionalquiz
	    			.orElseThrow(() -> new UsernameNotFoundException("No tipoQuiz " +
							"Found with tipoQuizId : " ));
	    	System.out.print("nooooooooofgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggooooo");
	     	List<TipoQuiz> tipiQuiz=   tipoQuizRepository.findByQuiz(quiz);
	    	
	     	for (TipoQuiz q:tipiQuiz) {
	     		TipoQuizDelete tipoQuizDelete=new TipoQuizDelete(q.getTipoQuizId());
	     		
	     		tipoquizDelete(tipoQuizDelete);
	    
	     	System.out.println("fatto");
	     	}
	     
	    quizRepository.deleteById("cu78ypgm");
	     return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
}
