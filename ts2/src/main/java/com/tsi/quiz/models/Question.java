package com.tsi.quiz.models;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {
	@Id
	@Column(unique = true)
	private String questionId;

	@Lob
	@NotNull
	private String question;

	
	private Long punteggio;
	@ManyToOne
	@JoinColumn(name = "tipo_quiz_id")
	TipoQuiz tipoQuiz;



 private boolean rispostaMultipla=false;



	@Column(name = "tempo")
	private    Long tempo;
}
