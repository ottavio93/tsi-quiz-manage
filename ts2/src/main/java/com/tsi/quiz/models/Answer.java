package com.tsi.quiz.models;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "risposte")
public class Answer {

	@Id
	@Column(unique = true,name = "id_risposta")
	 String answerId;

	@Lob
 String answer;
  

 boolean corretta;
 @ManyToOne
	@JoinColumn(name = "question_id")
	Question question;

}