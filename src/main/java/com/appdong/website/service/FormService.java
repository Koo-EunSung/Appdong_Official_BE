package com.appdong.website.service;

import com.appdong.website.dto.form.FormRequest;
import com.appdong.website.dto.form.FormResponse;
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
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void create(FormRequest.Create request) {
        Form form = new Form(request.getTitle(), request.getDescription());
        formRepository.save(form);

        List<Question> questions = new ArrayList<>();
        for (QuestionRequest.Create question : request.getQuestions())
            questions.add(new Question(null, form, question.getType(), question.getTitle(), question.getDescription(), null, question.getChoices()));
        questionRepository.saveAllInBulk(questions);
    }

    @Transactional(readOnly = true)
    public List<FormResponse.Introduction> getActiveForm() {
        return formRepository.findAllByActiveIsTrue()
                .stream()
                .map(FormResponse.Introduction::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public FormResponse.Detail getDetail(Long id) {
        return formRepository.findById(id)
                .map(FormResponse.Detail::from)
                .orElse(null);
    }

    @Transactional
    public FormResponse.Detail update(FormRequest.Update request) {
        Form form = formRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 설문지가 존재하지 않습니다."));
        String title = form.getTitle();
        String description = form.getDescription();

        if (request.getTitle() != null) {
            if (request.getTitle().isEmpty())
                throw new IllegalArgumentException("설문지의 제목은 비워둘 수 업습니다.");
            title = request.getTitle();
        }

        if (request.getDescription() != null)
            description = request.getDescription();

        form.update(title, description);

        if(request.isUpdateActive())
            form.activationToggle();

        return FormResponse.Detail.from(form);
    }
}
