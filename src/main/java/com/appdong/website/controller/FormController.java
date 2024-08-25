package com.appdong.website.controller;

import com.appdong.website.dto.form.FormRequest;
import com.appdong.website.dto.form.FormResponse;
import com.appdong.website.service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="1. Form", description = "설문지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/form")
public class FormController {
    private final FormService formService;

    @PostMapping
    @Operation(summary = "설문지 생성 API")
    public ResponseEntity<Void> createForm(@Valid @RequestBody FormRequest.Create request) {
        formService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "설문지 목록 조회 API")
    public ResponseEntity<List<FormResponse.Introduction>> getActiveFormIntro() {
        return ResponseEntity.ok().body(formService.getActiveForm());
    }

    @GetMapping("/{formId}")
    @Operation(summary = "설문지 상세 조회 API")
    public ResponseEntity<FormResponse.Detail> getFormDetail(@PathVariable Long formId) {
        return ResponseEntity.ok().body(formService.getDetail(formId));
    }
}
