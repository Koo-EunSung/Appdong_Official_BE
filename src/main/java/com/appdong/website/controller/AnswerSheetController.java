package com.appdong.website.controller;

import com.appdong.website.dto.answerSheet.AnswerSheetRequest;
import com.appdong.website.dto.answerSheet.AnswerSheetResponse;
import com.appdong.website.service.AnswerSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "2. AnswerSheet", description = "답변지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer-sheet")
public class AnswerSheetController {
    private final AnswerSheetService answerSheetService;

    @PostMapping
    @Operation(summary = "답변지 생성 API")
    public ResponseEntity<Void> createAnswerSheet(@Valid @RequestBody AnswerSheetRequest.Create request) {
        answerSheetService.createAnswerSheet(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "답변지 전체 조회 API", description = """
            특정 설문지의 답변지를 모두 조회한다.
            """)
    public ResponseEntity<AnswerSheetResponse.AllInfo> getAllAnswerSheet(@RequestParam Long formId) {
        return ResponseEntity.ok().body(answerSheetService.getAll(formId));
    }
}
