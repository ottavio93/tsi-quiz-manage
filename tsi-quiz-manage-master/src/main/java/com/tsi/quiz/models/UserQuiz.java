package com.tsi.quiz.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQuiz {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	 User user;

	    @ManyToOne
	    @JoinColumn(name = "quiz_id")
	    Quiz quiz;

	    LocalDateTime registeredAt;

	private String giudizioGlobale;
	
	private String giudizioPsicologico;
	
	private String giudizioTecnico;
}
