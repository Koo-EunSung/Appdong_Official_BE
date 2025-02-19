package com.appdong.website.entity.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {
    SIGN_UP("가입 신청"), DEMAND_SURVEY("수요 조사"), RSVP("참가 여부");

    private final String name;
}
