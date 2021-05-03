package com.tsi.quiz.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayAnswerResponse {
	private String answerId;
	private Long tempo;
	private String answer;
	
private String questionId;
private String playQuizId;

}
