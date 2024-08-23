package com.appdong.website.entity.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {
    SHORT_ANSWER,
    LONG_ANSWER,
    SELECT,
    DROP_DOWN;

}
