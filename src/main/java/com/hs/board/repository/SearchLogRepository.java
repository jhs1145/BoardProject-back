package com.hs.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hs.board.entity.SearchLogEntity;
import com.hs.board.entity.resultSet.SearchWordResultSet;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer>{
    @Query(
        value = "select search_word as searchWord, count(*) as count " +
        "from search_log " +
        "GROUP BY search_word " +
        "ORDER BY count desc " +
        "limit 15; ",
        nativeQuery = true
    )
    List<SearchWordResultSet> getTop15SearchWord();

    @Query(
        value = "SELECT relation_word as searchWord, count(*) as count " +
        "from search_log " +
        "WHERE search_word = ?1 " +
        "and relation_word is not null " +
        "GROUP BY searchWord " +
        "ORDER BY count desc " +
        "limit 15; ",
        nativeQuery = true
    )
    List<SearchWordResultSet> getRelationList(String searchWord);

}
