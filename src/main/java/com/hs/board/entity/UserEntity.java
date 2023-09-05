package com.hs.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hs.board.dto.request.auth.SignUpRequestDto;
import com.hs.board.dto.request.user.PatchUserNicknameRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String telNumber;
    private String address;
    private String addressDetail;
    private String profileImageUrl;

    public UserEntity (SignUpRequestDto dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
    }

    public void patchUserNickname(PatchUserNicknameRequestDto dto){
        this.nickname = dto.getNickname();
    }
    public void patchUserProfileImage(String profileImage){
        this.profileImageUrl = profileImage;
    }
}
