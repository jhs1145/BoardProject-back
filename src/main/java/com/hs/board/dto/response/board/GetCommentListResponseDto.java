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
public class GetCommentListResponseDto extends ResponseDto {
    private List<CommentListResponseDto> commentList;

    private GetCommentListResponseDto(String code, String message, List<CommentListResponseDto> commentList){
        super(code, message);
        this.commentList = commentList;
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentListResponseDto> commentList){
        GetCommentListResponseDto result = new GetCommentListResponseDto(ResponseCode.Success, ResponseMessage.Success, commentList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
