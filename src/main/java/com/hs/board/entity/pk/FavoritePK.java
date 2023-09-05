package com.hs.board.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 매개변수가 없는 생성자
@AllArgsConstructor // 모든 멤버변수 생성자
// 슈퍼키 지정 클래스
public class FavoritePK implements Serializable {
    @Column(name= "board_number")
    private int boardNumber;
    @Column(name= "user_email")
    private String userEmail;
}
