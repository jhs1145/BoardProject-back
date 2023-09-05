package com.hs.board.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hs.board.common.response.ResponseCode;
import com.hs.board.common.response.ResponseMessage;
import com.hs.board.dto.response.ResponseDto;
import com.hs.board.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//! GetUserResponseDto 랑 구성요소가 같지만, 서로 다른 API에 적용되기때문에 별도의 DTO가 필요하다.
public class GetSignInUserResponseDto extends ResponseDto{
    private String email;
    private String nickname;
    private String profileImageUrl;

    private GetSignInUserResponseDto(String code, String message, UserEntity userEntity){
        super(code, message);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImageUrl= userEntity.getProfileImageUrl();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity){
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(ResponseCode.Success, ResponseMessage.Success, userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 실패 - 존재하지않는 유저 //
    public static ResponseEntity<ResponseDto> noExistedUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_EXISTED_USER, ResponseMessage.NO_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
