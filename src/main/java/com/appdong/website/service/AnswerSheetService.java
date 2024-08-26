package com.appdong.website.service;

import com.appdong.website.dto.answer.AnswerRequest;
import com.appdong.website.dto.answerSheet.AnswerSheetRequest;
import com.appdong.website.dto.answerSheet.AnswerSheetResponse;
import com.appdong.website.dto.question.QuestionResponse;
import com.appdong.website.entity.form.Answer;
import com.appdong.website.entity.form.AnswerSheet;
import com.appdong.website.entity.form.Form;
import com.appdong.website.entity.form.Question;
import com.appdong.website.repository.answer.AnswerRepository;
import com.appdong.website.repository.answerSheet.AnswerSheetRepository;
import com.appdong.website.repository.form.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerSheetService {
    private final FormRepository formRepository;
    private final AnswerSheetRepository answerSheetRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public void createAnswerSheet(AnswerSheetRequest.Create request) {
        Form form = formRepository.findByIdFetchJoinQuestion(request.getFormId())
                .orElseThrow(() -> new NoSuchElementException("해당 설문지가 존재하지 않습니다."));

        if(!form.isActive())
            throw new IllegalStateException("해당 설문지는 응답을 받지 않습니다. 오류라고 판단되면 임원진에게 문의하세요.");

        Map<Long, Question> questions = form.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, question -> question));

        AnswerSheet answerSheet = new AnswerSheet(form, request.getName(), request.getStudentId());
        answerSheetRepository.save(answerSheet);

        List<Answer> answers = new ArrayList<>();
        for(AnswerRequest.Create create : request.getAnswer()) {
            if(!questions.containsKey(create.getQuestionId()))
                throw new NoSuchElementException("설문지에 해당 질문이 존재하지 않습니다.");
            answers.add(new Answer(null, answerSheet, questions.get(create.getQuestionId()), create.getAnswer()));
        }

        answerRepository.saveAllInBulk(answers);
    }

    @Transactional(readOnly = true)
    public AnswerSheetResponse.AllInfo getAll(Long formId) {
        Form form = formRepository.findByIdFetchJoinQuestion(formId)
                .orElseThrow(() -> new NoSuchElementException("해당 설문지가 존재하지 않습니다."));

        List<AnswerSheetResponse.Info> answerSheets = answerSheetRepository.findAllByFormIdFetchJoinAnswers(formId).stream()
                .map(AnswerSheetResponse.Info::from)
                .sorted(Comparator.comparing(AnswerSheetResponse.Info::getCreatedAt))
                .toList();

        List<QuestionResponse.Info> questions = form.getQuestions().stream()
                .map(QuestionResponse.Info::from)
                .toList();

        return new AnswerSheetResponse.AllInfo(questions, answerSheets);
    }
}
