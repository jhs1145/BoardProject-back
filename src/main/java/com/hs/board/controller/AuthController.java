package com.hs.board.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.board.dto.response.auth.SignInResponseDto;
import com.hs.board.dto.response.auth.SignUpResponseDto;
import com.hs.board.service.AuthService;

import lombok.RequiredArgsConstructor;

import com.hs.board.dto.request.auth.SignInRequestDto;
import com.hs.board.dto.request.auth.SignUpRequestDto;

// controller : 인증 컨트롤러 //
// 사용자로부터 입력을받고 출력을 내보내는 영역
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;
  
  // API : 회원가입 메서드 //
  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDto> signUp(
    @RequestBody @Valid SignUpRequestDto requestBody
  ) {
    // 서비스단에 데이터 dto를 넘겨주고 결과값만 받아서 리턴함
    ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
    return response;
  }

  // API : 로그인 메서드 //
  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDto> signIn(
    @RequestBody @Valid SignInRequestDto requestBody
  ) {
    ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
    return response;
  }

}
