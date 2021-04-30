package com.tsi.quiz.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "play_quiz")
public class PlayQuiz {
	@Id
	@Column()
	private String playQuizId;
    private String nome;
    private String cognome;
    @Column(name = "punteggiotecnico")
    private Long punteggioTecnico=(long) 0;
    private String giudizioPsicologico="da inserire";
    private String giudizioGlobale ="da inserire";
    private Boolean approvato=false;
    @NotEmpty 
    @Email(message = "Email should be valid")
	    private String email;
	 
	

	


	 
}