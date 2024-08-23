package com.appdong.website.controller;

import com.appdong.website.dto.form.FormRequest;
import com.appdong.website.service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
