package com.hs.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hs.board.dto.request.user.PatchUserNicknameRequestDto;
import com.hs.board.dto.request.user.PatchUserProfileRequestDto;
import com.hs.board.dto.response.ResponseDto;
import com.hs.board.dto.response.user.GetSignInUserResponseDto;
import com.hs.board.dto.response.user.GetUserResponseDto;
import com.hs.board.dto.response.user.PatchUserNicknameResponseDto;
import com.hs.board.dto.response.user.PatchUserProfileResponseDto;
import com.hs.board.entity.UserEntity;
import com.hs.board.repository.UserRepository;
import com.hs.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {
        UserEntity userEntity = null;
        try{
            // 이메일에 해당하는 유저 조회 //
            userEntity = userRepository.findByEmail(email);

            // 유저 데이터가 존재하는지 확인 //
            if(userEntity == null) return GetUserResponseDto.noExistedUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
        UserEntity userEntity = null;
        try{
            // 이메일에 해당하는 유저 조회 //
            userEntity = userRepository.findByEmail(email);

            // 유저 데이터가 존재하는지 확인 //
            if(userEntity == null) return GetSignInUserResponseDto.noExistedUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super PatchUserNicknameResponseDto> patchUserNickname(String email,
            PatchUserNicknameRequestDto dto) {
        
        String nickname = dto.getNickname();
        try{
            // 존재하는 유저인지 확인 //
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return PatchUserNicknameResponseDto.noExistedUser();

            // 중복되는 닉네임인지 확인 //
            boolean equalNickname = userEntity.getNickname().equals(nickname);
            if(equalNickname) return PatchUserNicknameResponseDto.existedNickname();

            // 수정 //
            userEntity.patchUserNickname(dto);

            // 데이터베이스에 저장 // 
            userRepository.save(userEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchUserNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchUserProfileResponseDto> patchUserProfile(String email,
            PatchUserProfileRequestDto dto) {
        String profileImage = dto.getProfileImage();
        try{
            // 존재하는 유저인지 확인 //
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return PatchUserProfileResponseDto.noExistedUser();

            // 수정 //
            userEntity.patchUserProfileImage(profileImage);

            // 데이터베이스에 저장 // 
            userRepository.save(userEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchUserProfileResponseDto.success();
    }
    
}
