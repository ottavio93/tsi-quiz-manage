package com.tsi.quiz.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUserQuiz {
    private String username;
    private String password;
    private String email;
}
