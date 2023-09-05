package com.hs.board.dto.response.user;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hs.board.common.response.ResponseCode;
import com.hs.board.common.response.ResponseMessage;
import com.hs.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserNicknameResponseDto extends ResponseDto {
    
    private PatchUserNicknameResponseDto (String code, String message){
        super(code, message);
    }    
    public static ResponseEntity<PatchUserNicknameResponseDto> success() {
        PatchUserNicknameResponseDto result = new PatchUserNicknameResponseDto(ResponseCode.Success, ResponseMessage.Success);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    public static ResponseEntity<ResponseDto> noExistedUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NO_EXISTED_USER, ResponseMessage.NO_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> existedNickname() {
        ResponseDto result = new ResponseDto(ResponseCode.EXISTED_NINCKNAME, ResponseMessage.EXISTED_NINCKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
      }
}
