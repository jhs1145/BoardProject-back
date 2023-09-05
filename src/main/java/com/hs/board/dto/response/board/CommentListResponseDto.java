package com.hs.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.hs.board.entity.resultSet.CommentListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// List의 요소는 ResponseDto 상속을 받지 않음
// 필요한 변수가 뭔지는 front의 response dto 를 참고하고
// 변수명은 데이터베이스의 명명을 참고?
public class CommentListResponseDto {
    private String ProfileImageUrl;
    private String nickname;
    private String contents;
    private String writeDateTime;

    private CommentListResponseDto(CommentListResultSet resultSet){
        this.ProfileImageUrl = resultSet.getProfileImageUrl();
        this.nickname = resultSet.getNickname();
        this.contents = resultSet.getContents();
        this.writeDateTime = resultSet.getWriteDatetime();
    }
    
    public static List<CommentListResponseDto> copyList(List<CommentListResultSet> resultSets) {
        List<CommentListResponseDto> commentList = new ArrayList<>();

        for(CommentListResultSet resultSet : resultSets){
            CommentListResponseDto comment = new CommentListResponseDto(resultSet);
            commentList.add(comment);
        }

        return commentList;
    }
}
