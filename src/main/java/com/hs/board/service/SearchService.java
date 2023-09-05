package com.hs.board.service;

import org.springframework.http.ResponseEntity;

import com.hs.board.dto.response.search.GetPopluarListResponseDto;
import com.hs.board.dto.response.search.GetRelationListResponseDto;

public interface SearchService {
    // 인기 검색어 리스트 불러오기 메서드 //
    ResponseEntity<? super GetPopluarListResponseDto> getPopularList();

    // 연관 검색어 리스트 불러오기 메서드 //
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
}
