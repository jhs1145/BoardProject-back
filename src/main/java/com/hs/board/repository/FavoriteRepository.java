package com.hs.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hs.board.entity.FavoriteEntity;
import com.hs.board.entity.pk.FavoritePK;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePK>{
    boolean existsByUserEmailAndBoardNumber(String email, Integer boardNumber);
    

    @Transactional //! 
    void deleteByBoardNumber(Integer boardNumber);
}
