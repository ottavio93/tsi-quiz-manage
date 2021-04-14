package com.tsi.quiz.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoQuiz {
	
	 @Id
	@Column(unique = true,name = "argomentoQuizId")
	    private String tipoQuizId;
	
	 @ManyToOne
	    @JoinColumn(name = "quiz_id")
	 Quiz quiz;
	 @Column(unique = false)
	 String argomento;
}

