package com.tsi.quiz.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "play_risposte")
public class PlayAnswer {
	@Id
	@Column(unique = true)
	private String playAnswerId;
	private Long tempo;
	 @Lob
	 @Column(name = "risposta")
	 String answer;
	 boolean corretta;
	 @ManyToOne
		@JoinColumn(name = "question_id")
		Question question;
	 
	 @ManyToOne
		@JoinColumn(name = "quiz_id")
	 PlayQuiz playQuiz;
	 
	 
	
	 @Column(name = "punteggi")
		Long punteggio;
	
	 
}

