package com.tsi.quiz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

	 @Id
	 @Column(unique = true,name = "quiz_id")
	 private String quizId;
	
	 private String nome;
	 private  String seniority;
	 private String  createdDate;
	 private String description;
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	 User user;
}
