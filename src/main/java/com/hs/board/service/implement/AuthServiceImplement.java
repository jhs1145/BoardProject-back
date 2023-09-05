package com.hs.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hs.board.dto.request.auth.SignInRequestDto;
import com.hs.board.dto.request.auth.SignUpRequestDto;
import com.hs.board.dto.response.ResponseDto;
import com.hs.board.dto.response.auth.SignInResponseDto;
import com.hs.board.dto.response.auth.SignUpResponseDto;
import com.hs.board.entity.UserEntity;
import com.hs.board.provider.JwtProvider;
import com.hs.board.repository.UserRepository;
import com.hs.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    //!       로그인 메서드            //
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;

        try{
            // 이메일로 entity 조회 //
            UserEntity userEntity = userRepository.findByEmail(dto.getEmail());

            // 존재하지 않는 email 확인 //
            if(userEntity == null) return SignInResponseDto.signInDataMismatch();

            // 비밀번호 일치여부 확인 //
            String encodedPassword = userEntity.getPassword();
            boolean equalPassword = passwordEncoder.matches(dto.getPassword(), encodedPassword);
            if( !equalPassword ) return SignInResponseDto.signInDataMismatch();

            // JWT 생성 //
            token = jwtProvider.create(dto.getEmail());

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

    @Override
    //!        회원가입 메서드         //
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) { // POSTMAN에서 JSON 형태로 보내는 객체가 Request DTO에 담기고
        // 필요 데이터 파싱해와서
        String email = dto.getEmail();
        String password = dto.getPassword();
        String nickname = dto.getNickname();
        String telNumber = dto.getTelNumber();

        try{
            // 이메일 중복 확인 //
            boolean hasEmail = userRepository.existsByEmail(email); // Repository는 DB에서 필요한 로직을 수행 후 결과를 리턴하고 
            if(hasEmail) return SignUpResponseDto.existedEmail(); // Response DTO는 DB에 서버상태 및 결과값(Response Body)을 보냄.

            // 닉네임 중복 확인 //
            boolean hasNinckname = userRepository.existsByNickname(nickname);
            if(hasNinckname) return SignUpResponseDto.existedNickname();

            // 전화번호 중복 확인 //
            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            if(hasTelNumber) return SignUpResponseDto.existedTelNumber();

            // 비밀번호 암호화 //
            password = passwordEncoder.encode(password);

            // dto의 패스워드 변경 //
            dto.setPassword(password);

            // Entity 생성 //
            UserEntity userEntity = new UserEntity(dto); // 입력값(위 POSTMAN에서 JSON 형태로 보내는 객체 Request DTO)을 entity에 담고

            // 데이터베이스에 저장 //
            userRepository.save(userEntity); // Repository는 entity가 연결된 DB & TABLE 에 save 로직을 수행함.

            //! DTO : 유저가 입력하는 입력값(Request DTO) & 로직에 따라 반환되는 결과값(Response DTO)이 있고, Response DTO는 서버상태와 Response Body를 반환함.
            //! Entity : DB의 특정 TABLE과 연동되고, 해당 TABLE에 저장될 데이터(입력값 : Request DTO)가 담길 객체
            //! Repository : DB에서 로직을 수행함.

        }catch (Exception exception){
            exception.printStackTrace(); // 콘솔창에 에러메세지가 뜸.
            return ResponseDto.databaseError(); //  500 에러
        }
        
        return SignUpResponseDto.success();
    }
    
}
