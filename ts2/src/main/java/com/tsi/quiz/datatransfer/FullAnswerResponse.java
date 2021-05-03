
package com.tsi.quiz.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullAnswerResponse {
	private String answerId;
	private Long tempo;
	private  boolean corretta;
	private String answer;
	private Long punteggio;
private String questionId;
private String playQuizId;

}
