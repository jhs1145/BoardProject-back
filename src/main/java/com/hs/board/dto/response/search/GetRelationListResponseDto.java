package com.hs.board.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hs.board.common.response.ResponseCode;
import com.hs.board.common.response.ResponseMessage;
import com.hs.board.dto.response.ResponseDto;
import com.hs.board.entity.resultSet.SearchWordResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRelationListResponseDto extends ResponseDto{
    
    private List<String> relationList;

    private GetRelationListResponseDto(String code, String message, List<SearchWordResultSet> resultSets){
        super(code, message);

        List<String> relationList = new ArrayList<>();

        for(SearchWordResultSet resultSet : resultSets){
            String searchWord = resultSet.getSearchWord();
            relationList.add(searchWord);
        }
        this.relationList = relationList;
    }

    public static ResponseEntity<GetRelationListResponseDto> success(List<SearchWordResultSet> resultSets) {
        GetRelationListResponseDto result = new GetRelationListResponseDto(ResponseCode.Success, ResponseMessage.Success, resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
