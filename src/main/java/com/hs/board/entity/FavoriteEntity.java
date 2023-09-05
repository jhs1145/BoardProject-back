package com.hs.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.hs.board.entity.pk.FavoritePK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 매개변수가 없는 생성자
@AllArgsConstructor // 모든 멤버변수 생성자
@Entity(name = "favorite")
@Table(name = "favorite")
@IdClass(FavoritePK.class) // 슈퍼키 지정
public class FavoriteEntity {
    @Id
    private int boardNumber;
    @Id
    private String userEmail;

    public FavoriteEntity(Integer  boardNumber, String userEmail){
        this.boardNumber = boardNumber;
        this.userEmail = userEmail;
    }
}
