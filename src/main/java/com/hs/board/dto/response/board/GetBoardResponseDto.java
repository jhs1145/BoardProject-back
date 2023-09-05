package com.hs.board.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hs.board.common.response.ResponseCode;
import com.hs.board.common.response.ResponseMessage;
import com.hs.board.dto.response.ResponseDto;
import com.hs.board.entity.BoardViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetBoardResponseDto extends ResponseDto {
    private int boardNumber;
    private String title;
    private String contents;
    private String imageUrl;
    private String writeDatetime;
    private String writerEmail;
    private String writerProfileImage;
    private String writerNickname;

    private GetBoardResponseDto(String code, String message, BoardViewEntity boardView){
        super(code, message);
        this.boardNumber = boardView.getBoardNumber();
        this.title = boardView.getTitle();
        this.contents = boardView.getContents();
        this.imageUrl = boardView.getImageUrl();
        this.writeDatetime = boardView.getWriteDatetime();
        this.writerEmail = boardView.getWriterEmail();
        this.writerProfileImage = boardView.getWriterProfileImage();
        this.writerNickname = boardView.getWriterNickname();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardViewEntity boardView){
        GetBoardResponseDto result = new GetBoardResponseDto(ResponseCode.Success, ResponseMessage.Success, boardView);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistedBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_EXISTED_BOARD, ResponseMessage.NO_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
