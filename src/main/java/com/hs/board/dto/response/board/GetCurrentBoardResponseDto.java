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
public class GetCurrentBoardResponseDto extends ResponseDto {

    private List<BoardListResponseDto> boardList;

    private GetCurrentBoardResponseDto(String code, String message, List<BoardListResponseDto> boardList){
        super(code, message);
        this.boardList = boardList;
    }

    public static ResponseEntity<GetCurrentBoardResponseDto> success(List<BoardListResponseDto> boardList){
        GetCurrentBoardResponseDto result = new GetCurrentBoardResponseDto(ResponseCode.Success, ResponseMessage.Success, boardList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
