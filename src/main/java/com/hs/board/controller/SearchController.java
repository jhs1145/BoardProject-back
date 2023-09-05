package com.hs.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hs.board.dto.response.search.GetPopluarListResponseDto;
import com.hs.board.dto.response.search.GetRelationListResponseDto;
import com.hs.board.service.SearchService;

import lombok.RequiredArgsConstructor;

// controller: 검색 컨트롤러 //
@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
  
  private final SearchService searchService;
  // API : 인기 검색어 리스트 불러오기 메서드 //
  @GetMapping("/popular")
  public ResponseEntity<? super GetPopluarListResponseDto> getPopularList() {
    ResponseEntity<? super GetPopluarListResponseDto> response = searchService.getPopularList();
    return response;
  }

  // API : 연관 검색어 리스트 불러오기 메서드 //
  @GetMapping("/relation/{searchWord}")
  public ResponseEntity<? super GetRelationListResponseDto> getRelationList(
    @PathVariable(value = "searchWord", required = true) String searchWord
  ) {
    ResponseEntity<? super GetRelationListResponseDto> response = searchService.getRelationList(searchWord);
    return response;
  }

}
