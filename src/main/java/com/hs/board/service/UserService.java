package com.hs.board.service;

import org.springframework.http.ResponseEntity;

import com.hs.board.dto.request.user.PatchUserNicknameRequestDto;
import com.hs.board.dto.request.user.PatchUserProfileRequestDto;
import com.hs.board.dto.response.user.GetSignInUserResponseDto;
import com.hs.board.dto.response.user.GetUserResponseDto;
import com.hs.board.dto.response.user.PatchUserNicknameResponseDto;
import com.hs.board.dto.response.user.PatchUserProfileResponseDto;

public interface UserService {
    // 유저 정보 불러오기 메서드 //
    ResponseEntity<? super GetUserResponseDto> getUser(String email);

    // 로그인 유저 정보 불러오기 메서드 //
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

    // 유저 닉네임 수정 메서드 //
    ResponseEntity<? super PatchUserNicknameResponseDto> patchUserNickname(String email, PatchUserNicknameRequestDto dto);

    // 유저 프로필 이미지 수정 메서드 //
    ResponseEntity<? super PatchUserProfileResponseDto> patchUserProfile(String email, PatchUserProfileRequestDto dto);
}
