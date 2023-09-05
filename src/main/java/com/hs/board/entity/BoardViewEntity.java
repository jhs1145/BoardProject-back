package com.hs.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_view")
@Table(name = "board_view" )
// View 객체를 만듬으로서 인터페이스를 쓰지않고 바로담는다?
public class BoardViewEntity {
    @Id
    private int boardNumber;
    private String title;
    private String contents;
    private String imageUrl;
    private int viewCount;
    private int commentCount;
    private int favoriteCount;
    private String writeDatetime;
    private String writerEmail;
    private String writerProfileImage;
    private String writerNickname; 
}
