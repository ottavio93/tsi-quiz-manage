package com.tsi.quiz.datatransfer;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {
	private String answerId;
	private String answer;
	private boolean corretta;
private String questionId;
}
