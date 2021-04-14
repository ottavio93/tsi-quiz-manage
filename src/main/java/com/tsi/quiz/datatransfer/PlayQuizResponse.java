package com.tsi.quiz.datatransfer;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayQuizResponse {
	private String playQuizId;
	 
	 private String email;
private String cognome;
private String nome;
}
