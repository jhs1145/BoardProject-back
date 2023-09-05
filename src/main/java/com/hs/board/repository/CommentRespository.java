package com.hs.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hs.board.entity.CommentEntity;
import com.hs.board.entity.resultSet.CommentListResultSet;

@Repository
public interface CommentRespository extends JpaRepository<CommentEntity, Integer>{

    @Query(
        value = "SELECT u.profile_image_url as profileImageUrl, u.nickname as nickname, c.contents as contents, c.write_datetime as writeDatetime " +
            "from comment as c " +
            "INNER JOIN user as u " +
            "on c.user_email = u.email " +
            "WHERE board_number= ?1 " +
            "order by c.write_datetime; ",
            nativeQuery=true
    )
    List<CommentListResultSet> getCommentList(Integer boardNumber);


    @Transactional
    void deleteByBoardNumber(Integer boardNumber); // 이름으로 자동으로 기능을 정의해줌
}
