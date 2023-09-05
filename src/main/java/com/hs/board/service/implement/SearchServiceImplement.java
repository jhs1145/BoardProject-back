package com.hs.board.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hs.board.dto.response.ResponseDto;
import com.hs.board.dto.response.search.GetPopluarListResponseDto;
import com.hs.board.dto.response.search.GetRelationListResponseDto;
import com.hs.board.entity.resultSet.SearchWordResultSet;
import com.hs.board.repository.SearchLogRepository;
import com.hs.board.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService{

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopluarListResponseDto> getPopularList() {
        List<SearchWordResultSet> resultSets = null;

        try{
            // 인기검색어 리스트 불러오기
            resultSets = searchLogRepository.getTop15SearchWord();


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopluarListResponseDto.success(resultSets);
        
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {
        List<SearchWordResultSet> resultSets = null;

        try{
            // 연관 검색어 리스트 불러오기 //
            resultSets = searchLogRepository.getRelationList(searchWord);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRelationListResponseDto.success(resultSets);
    }
    
}
