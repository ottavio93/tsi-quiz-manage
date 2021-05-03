package com.tsi.quiz.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequest {
	 private String quizId;
	 private String nome;
	 private  String difficolta;
	 private String description;
	 private String userName;
}
