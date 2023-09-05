package com.hs.board.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hs.board.common.response.ResponseCode;
import com.hs.board.common.response.ResponseMessage;
import com.hs.board.dto.response.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetFavoriteListResponseDto extends ResponseDto{
    // List<UserEntity> userList; //! : 개인정보가 들어있는 객체라서 필요없는 데이터까지 다 들어가기때문에 따로 클래스를 만들어서 작업해주어야함.
    List<FavoriteListResponseDto> favoriteList;

    private GetFavoriteListResponseDto(String code, String message, List<FavoriteListResponseDto> favoriteList){
        super(code, message);
        this.favoriteList = favoriteList;
    }

    public static ResponseEntity<GetFavoriteListResponseDto> success(List<FavoriteListResponseDto> favoriteList){
        GetFavoriteListResponseDto result = new GetFavoriteListResponseDto(ResponseCode.Success, ResponseMessage.Success, favoriteList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
