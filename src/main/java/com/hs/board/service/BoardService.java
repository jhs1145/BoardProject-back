package com.hs.board.service;

import org.springframework.http.ResponseEntity;

import com.hs.board.dto.request.board.PostBoardRequestDto;
import com.hs.board.dto.request.board.PatchBoardRequestDto;
import com.hs.board.dto.request.board.PostCommentRequestDto;
import com.hs.board.dto.response.board.DeleteBoardResponseDto;
import com.hs.board.dto.response.board.GetBoardResponseDto;
import com.hs.board.dto.response.board.GetCommentListResponseDto;
import com.hs.board.dto.response.board.GetCurrentBoardResponseDto;
import com.hs.board.dto.response.board.GetFavoriteListResponseDto;
import com.hs.board.dto.response.board.GetSearchBoardResponseDto;
import com.hs.board.dto.response.board.GetTop3ResponseDto;
import com.hs.board.dto.response.board.GetUserListResponseDto;
import com.hs.board.dto.response.board.PatchBoardResponseDto;
import com.hs.board.dto.response.board.PostBoardResponseDto;
import com.hs.board.dto.response.board.PostCommentResponseDto;
import com.hs.board.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {

    // method : TOP3 게시물 불러오기 메소드
    ResponseEntity<? super GetTop3ResponseDto> getTop3();
    // 최신 게시물 리스트 불러오기 메서드 //
    ResponseEntity<? super GetCurrentBoardResponseDto> getCurrentBoard(Integer section);
    // 게시물 불러오기 메서드 //
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    // 검색 게시물 리스트 불러오기 메서드 //
    ResponseEntity<? super GetSearchBoardResponseDto> getSearchBoard(String searchWord, String relationWord);
    // 특정 게시물의 좋아요 리스트 불러오기 메서드 //
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoritList(Integer boardNumber);
    // 특정 게시물의 댓글 리스트 불러오 메서드 // 
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
    // 특정 유저의 게시물 리스트 불러오기 메서드 //
    ResponseEntity<? super GetUserListResponseDto> getUserList(String email);

    // API : 게시물 작성 메서드 //
    ResponseEntity<? super PostBoardResponseDto> postBoard(String email, PostBoardRequestDto dto);
    // API : 댓글 작성 메서드 //
    ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardNumber,PostCommentRequestDto dto, String email);
    // 좋아요 기능 메서드 //
    ResponseEntity<? super PutFavoriteResponseDto> putFavorit(Integer boardNumber, String email);
    // API : 게시물 수정 메서드 //
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer boardNumber, PatchBoardRequestDto dto, String email);
    // API : 게시물 삭제 메서드 //
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
}

