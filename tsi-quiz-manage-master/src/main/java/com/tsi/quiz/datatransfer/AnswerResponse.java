package com.tsi.quiz.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
	private String answerId;
	private String answer;

private String questionId;
}
