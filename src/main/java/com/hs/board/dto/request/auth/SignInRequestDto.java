package com.hs.board.dto.request.auth;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
  //! 숫자 필수값 지정은 int -> Integer / double -> Double 로 지정하고 NotNull 을 지정해야한다 
  @NotBlank
  private String email;
  @NotBlank
  private String password;
  
}
