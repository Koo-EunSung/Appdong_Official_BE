package com.appdong.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "3. Temp", description = "CI/CD 작동 확인용 테스트 api 입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/temp")
public class TempController {
    @GetMapping
    @Operation(summary = "테스트 API")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok().body("GET API 응답");
    }
}
