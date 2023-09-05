package com.hs.board.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hs.board.common.response.ResponseCode;
import com.hs.board.common.response.ResponseMessage;
import com.hs.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto extends ResponseDto {
  
  private SignUpResponseDto (String code, String message) {
    super(code, message);
  }

  public static ResponseEntity<ResponseDto> success() {
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.Success, ResponseMessage.Success);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  // 존재하는 이메일 에러 처리
  public static ResponseEntity<ResponseDto> existedEmail() {
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.EXISTED_EMAIL, ResponseMessage.EXISTED_EMAIL);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  // 존재하는 닉네임 에러 처리
  public static ResponseEntity<ResponseDto> existedNickname(){
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.EXISTED_NINCKNAME, ResponseMessage.EXISTED_NINCKNAME);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  // 존재하는 전화번호 에러 처리
  public static ResponseEntity<ResponseDto> existedTelNumber(){
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.EXISTED_TEL_NUMBER, ResponseMessage.EXISTED_TEL_NUMBER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
