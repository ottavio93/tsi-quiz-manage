package com.tsi.quiz.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
	private String questionId;
	private String question;
	private  Long tempo;
	private Long punteggio;
	private String tipoQuizId;
}
