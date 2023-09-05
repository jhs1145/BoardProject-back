package com.hs.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hs.board.entity.BoardEntity;
import com.hs.board.entity.resultSet.BoardListResultSet;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
    boolean existsByBoardNumber(Integer boardNUmber);
    BoardEntity findByBoardNumber(Integer boardNumber);

    @Query(
        value = 
            "select " +
                "b.board_number AS boardNumber, " +
                "b.title as title, " +
                "b.contents as contents, " +
                "b.image_url as imageUrl, " +
                "b.view_count as viewCount, " +
                "b.comment_count as commentCount, " +
                "b.favorite_count as favoriteCount, " +
                "b.write_datetime as writeDateTime, " +
                "u.profile_image_url as writerProfileImage, " +
                "u.nickname as wrtierNickname " +
            "from board as b " +
            "INNER JOIN user as u " +
            "on b.writer_email = u.email " +
            "order by b.write_datetime desc " +
            "limit ?1, 50",
            nativeQuery = true // 표준 sql 처리를 해서 에러날 일이 적다
    )
    List<BoardListResultSet> getCurrentList(Integer section);
}
