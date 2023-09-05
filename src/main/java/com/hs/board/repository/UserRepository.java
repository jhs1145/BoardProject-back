package com.hs.board.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hs.board.entity.UserEntity;
import com.hs.board.entity.resultSet.CommentListResultSet;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByTelNumber(String telNumber);

    UserEntity findByEmail(String email); // Email은 pk라 유일한 값

    @Query(
        value = 
            "SELECT * " +
            "from user  " +
            "where email in (  " +
            " select user_email " +
            " from favorite " +
            " WHERE board_number= ?1); ",
            nativeQuery = true
    ) // 변수로 받는 값은 첫번째 매개변수는 ?1 어디든 쓸수있음. 두번째는 ?2
    // 데이터를 불러올때 entity에 있는 멤버변수들의 값은 필수요소라서 *를 함.
    List<UserEntity> getFavoriteList(Integer boardNumber);


    @Query(
        value = "SELECT u.profile_image_url as profileImageUrl, u.nickname as nickname, c.contents as contents, c.write_datetime as writeDatetime " +
            "from comment as c " +
            "INNER JOIN user as u " +
            "on c.user_email = u.email " +
            "WHERE board_number= ?1 ; ",
            nativeQuery=true
    )
    List<CommentListResultSet> getCommentList(Integer boardNumber);
}
