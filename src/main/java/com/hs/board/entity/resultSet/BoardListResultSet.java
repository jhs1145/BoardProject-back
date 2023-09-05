package com.hs.board.entity.resultSet;

public interface BoardListResultSet {
    int getBoardNumber();
    String getTitle();
    String getContents();
    String getImageUrl();
    int getViewCount();
    int getCommentCount();
    int getFavoriteCount();
    String getWriteDateTime();
    String getWriterProfileImage();
    String getWrtierNickname();
    
}
