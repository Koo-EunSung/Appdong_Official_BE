package com.appdong.website.service;

import com.appdong.website.dto.form.FormRequest;
import com.appdong.website.dto.question.QuestionRequest;
import com.appdong.website.entity.form.Form;
import com.appdong.website.entity.form.Question;
import com.appdong.website.repository.form.FormRepository;
import com.appdong.website.repository.form.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void create(FormRequest.Create request) {
        Form form = new Form(null, request.getTitle(), request.getDescription(), null, null);
        formRepository.save(form);

        List<Question> questions = new ArrayList<>();
        for(QuestionRequest.Create question : request.getQuestions())
            questions.add(new Question(null, form, question.getType(), question.getTitle(), question.getDescription(), null, question.getChoices()));
        questionRepository.saveAll(questions);
    }
}
