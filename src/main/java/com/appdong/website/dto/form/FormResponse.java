package com.appdong.website.dto.form;

import com.appdong.website.entity.form.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class FormResponse {
    @Getter
    @AllArgsConstructor
    public static class Introduction {
        private Long id;
        private String title;
        private String description;

        public static Introduction from(Form form) {
            return new Introduction(form.getId(), form.getTitle(), form.getDescription());
        }
    }
}
