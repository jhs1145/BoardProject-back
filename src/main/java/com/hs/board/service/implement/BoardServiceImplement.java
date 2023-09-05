package com.hs.board.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hs.board.dto.request.board.PatchBoardRequestDto;
import com.hs.board.dto.request.board.PostBoardRequestDto;
import com.hs.board.dto.request.board.PostCommentRequestDto;
import com.hs.board.dto.response.ResponseDto;
import com.hs.board.dto.response.board.BoardListResponseDto;
import com.hs.board.dto.response.board.CommentListResponseDto;
import com.hs.board.dto.response.board.DeleteBoardResponseDto;
import com.hs.board.dto.response.board.FavoriteListResponseDto;
import com.hs.board.dto.response.board.GetBoardResponseDto;
import com.hs.board.dto.response.board.GetCommentListResponseDto;
import com.hs.board.dto.response.board.GetCurrentBoardResponseDto;
import com.hs.board.dto.response.board.GetFavoriteListResponseDto;
import com.hs.board.dto.response.board.GetSearchBoardResponseDto;
import com.hs.board.dto.response.board.GetTop3ResponseDto;
import com.hs.board.dto.response.board.GetUserListResponseDto;
import com.hs.board.dto.response.board.PatchBoardResponseDto;
import com.hs.board.dto.response.board.PostBoardResponseDto;
import com.hs.board.dto.response.board.PostCommentResponseDto;
import com.hs.board.dto.response.board.PutFavoriteResponseDto;
import com.hs.board.entity.BoardEntity;
import com.hs.board.entity.BoardViewEntity;
import com.hs.board.entity.CommentEntity;
import com.hs.board.entity.FavoriteEntity;
import com.hs.board.entity.SearchLogEntity;
import com.hs.board.entity.UserEntity;
import com.hs.board.entity.resultSet.BoardListResultSet;
import com.hs.board.entity.resultSet.CommentListResultSet;
import com.hs.board.repository.BoardRepository;
import com.hs.board.repository.BoardViewRepository;
import com.hs.board.repository.CommentRespository;
import com.hs.board.repository.FavoriteRepository;
import com.hs.board.repository.SearchLogRepository;
import com.hs.board.repository.UserRepository;
import com.hs.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRespository commentRespository;
    private final FavoriteRepository favoriteRepository;
    private final BoardViewRepository boardViewRepository;
    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetTop3ResponseDto> getTop3() {
        List<BoardListResponseDto> top3 = null;
        try{
            // 좋아요 순으로 상위 3개 게시물 조회 //
            List<BoardViewEntity> boardViewEntities = boardViewRepository.findTop3ByOrderByFavoriteCountDesc(); 

            // entity를 dto 형태로 변환 //
            top3 = BoardListResponseDto.copyEntityList(boardViewEntities);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTop3ResponseDto.success(top3);
    }

    // API : 최신 게시물 리스트 불러오기 메서드 //
    // request dto 없이 불러오기만 함 response dto에 데이터를 받아와서 그대로 뿌려준다.
    //! GetCurrentBoardResponseDto : 비즈니스 로직이랑은 상관없이 정리된 데이터를 받아서 결과값만 보여주는 dto //
    //! BoardListResultSet :  boardRepository.getCurrentList() 에서 query를 이용해서 db에서 받아온 데이터가 담기는 interface //
    //! List<BoardListResultSet> : 해당 interface의 리스트니까 db의 모든 데이터가 담긴다고 보면되겠지. //
    //! BoardListResponseDto :  copyList(resultSets) 메서드로 db데이터를 객체로만든 class //  
    @Override
    public ResponseEntity<? super GetCurrentBoardResponseDto> getCurrentBoard(Integer section) {
        List<BoardListResponseDto> boardList = null;
        
        try{
            // 최신 게시물 리스트 불러오기 //
            Integer limit = (section -1) * 50;
            List<BoardListResultSet> resultSets = boardRepository.getCurrentList(limit); 
            boardList = BoardListResponseDto.copyList(resultSets);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCurrentBoardResponseDto.success(boardList);
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        BoardViewEntity boardViewEntity = null;
        
        try{
            // 게시물 번호에 해당하는 게시물 조회 //
            boardViewEntity = boardViewRepository.findByBoardNumber(boardNumber);

            // 존재하는 게시물인지 확인
            if(boardViewEntity == null) return GetBoardResponseDto.noExistedBoard();

            // 게시물 조회수 1 증가 //
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            boardEntity.increaseViewCount(); // todo 왜 조회수가 2씩 오를까

            // 데이터 베이스에 저장 //
            boardRepository.save(boardEntity);
            
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(boardViewEntity);
    }

    // API : 검색 게시물 리스트 불러오기 메서드 //
    @Override
    public ResponseEntity<? super GetSearchBoardResponseDto> getSearchBoard(String searchWord, String relationWord) {
        List<BoardListResponseDto> boardList = null;
        
        try{
            // 검색어가 제목과 내용에 포함되어 있는 데이터 조회 //
            List<BoardViewEntity> boardViewEntities = boardViewRepository.findByTitleContainsOrContentsContainsOrderByWriteDatetimeDesc(searchWord, searchWord); 

            // entity를 dto 형태로 변환 //
            boardList = BoardListResponseDto.copyEntityList(boardViewEntities);

            SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, relationWord);
            searchLogRepository.save(searchLogEntity);
            
            // 첫번째 검색이 아닐 경우 ( relationWord가 널이 아님 )
            if( relationWord != null ){
                searchLogEntity = new SearchLogEntity(relationWord, searchWord);
                searchLogRepository.save(searchLogEntity);
            }
            


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSearchBoardResponseDto.success(boardList);
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoritList(Integer boardNumber) { 
        List<FavoriteListResponseDto> favoriteList = null;
        
        try{
            // 게시물 번호의 좋아요 리스트 조회
            List<UserEntity> userEntities = userRepository.getFavoriteList(boardNumber);

            // entity를 dto로 변환 //
            favoriteList = FavoriteListResponseDto.copyList(userEntities);

            //?  존재하는 게시물인지


        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(favoriteList);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
        List<CommentListResponseDto> commentList = null;
        
        try{
            // 게시물 번호의 코멘트 리스트 조회
            List<CommentListResultSet> resultSets = commentRespository.getCommentList(boardNumber);
            //? userRepository에서 쿼리 불러올 땐 JPA-style positional param was not an integral ordinal 에러가 났는데,
            //? 쿼리는 어차피 같은데 레포지터리에 따라 에러가 나는 차이가 왜 있는지
            commentList = CommentListResponseDto.copyList(resultSets);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(commentList);
    }

    // API : 특정 유저의 게시물 리스트 불러오기 메서드 //
    @Override
    public ResponseEntity<?  super GetUserListResponseDto> getUserList(String email) {
        List<BoardListResponseDto> boardList = null;
        
        try{
            // 특정 이메일에 해당하는 게시물 리스트 조회 //
            List<BoardViewEntity> boardViewEntities = boardViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);

            // entity를 dto 형태로 변환 //
            boardList = BoardListResponseDto.copyEntityList(boardViewEntities);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserListResponseDto.success(boardList);
    }
    
    //! DTO : 유저가 입력하는 입력값(Request DTO) & 로직에 따라 반환되는 결과값(Response DTO)이 있고, Response DTO는 서버상태와 Response Body를 반환함.
    //! Entity : DB의 특정 TABLE과 연동되고, 해당 TABLE에 저장될 데이터(입력값 : Request DTO)가 담길 객체
    //! Repository : DB에서 로직을 수행함.
    
    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(String writerEmail, PostBoardRequestDto dto) {
        
        try{
            // 작성자 이메일이 존재하는 이메일인지 확인 //
            boolean hasUser = userRepository.existsByEmail(writerEmail);
            if ( !hasUser ) return PostBoardResponseDto.noExistedUser();
            
            // entity 생성 //
            BoardEntity boardEntity = new BoardEntity(writerEmail, dto);

            // 데이터베이스에 저장 //
            boardRepository.save(boardEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardNumber, PostCommentRequestDto dto, String userEmail) {
        try{

            // 존재하는 이메일인지 //
            boolean hasUser = userRepository.existsByEmail(userEmail);
            if (!hasUser) return PostCommentResponseDto.noExistedUser();

            // 존재하는 게시물인지 //
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity==null) return PostCommentResponseDto.noExistedBoard();

            // entity 생성 //
            CommentEntity commentEntity = new CommentEntity(boardNumber, userEmail, dto);

            // 데이터베이스에 저장 //
            commentRespository.save(commentEntity);

            // 게시물 댓글수 1 증가 //
            boardEntity.increaseCommentCount();

            // 데이터 베이스에 저장 //
            boardRepository.save(boardEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorit(Integer boardNumber, String userEmail) {
        
        try{

            // 존재하는 회원인지 확인 //
            boolean hasUser = userRepository.existsByEmail(userEmail);
            if (!hasUser) return PutFavoriteResponseDto.noExistedUser();
            
            // 존재하는 게시물인지 확인 //
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity==null) return PutFavoriteResponseDto.noExistedBoard();

            // 해당 유저가 해당 게시물에 좋아요 했는지 확인 //
            boolean isFavorite = favoriteRepository.existsByUserEmailAndBoardNumber(userEmail, boardNumber);
            
            // entity 생성 //
            FavoriteEntity favoriteEntity = new FavoriteEntity(boardNumber, userEmail);
            
            // 좋아요 상태 : 좋아요 X로 만듬
            if(isFavorite){
                favoriteRepository.delete(favoriteEntity);

                // 게시물 좋아요 1 감소 //
                boardEntity.decreaseFavoriteCount();
            } 
            // 좋아요 X 상태 : 좋아요로 만들어줌
            else{
                favoriteRepository.save(favoriteEntity);  

                // 게시물 좋아요 1 증가 //
                boardEntity.increaseFavoriteCount();
            }
            
            // 데이터 베이스에 저장 //
            boardRepository.save(boardEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer boardNumber, PatchBoardRequestDto dto, String userEmail) {
        
        try{
            
            // 존재하는 유저인지 //
            boolean hasUser = userRepository.existsByEmail(userEmail);
            if (!hasUser) return PatchBoardResponseDto.noExistedUser();
            
             // 존재하는 게시물인지 //
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return PatchBoardResponseDto.noExistedBoard();
            
            // 작성자와 이메일이 같은지 //
            boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return PatchBoardResponseDto.noPermission();
            
            // 위에서 boardNumber로 찾은 해당 entity의 내용을 patch 메서드로 바꿔줌 //
            boardEntity.patch(dto);

            // 데이터베이스에 저장 //
            boardRepository.save(boardEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {
        try{
            // 게시글을 삭제하려면 board 테이블을 참조하고있는
            // comment 와 favorite 테이블의 데이터를 먼저 삭제 해줘야함
            // cascade 를 걸어놨으면 자동삭제가 되겠지만 현재는 걸지않아 직접 삭제가 필요한 상황

            // 존재하는 유저인지 //
            boolean hasUser = userRepository.existsByEmail(email);
            if (!hasUser) return DeleteBoardResponseDto.noExistedUser();

            // 존재하는 게시물인지 //
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return DeleteBoardResponseDto.noExistedBoard();

            // 작성자와 이메일이 같은지 //
            boolean equalWriter = boardEntity.getWriterEmail().equals(email);
            if (!equalWriter) return DeleteBoardResponseDto.noPermission();

            // 댓글 데이터 삭제 //
            commentRespository.deleteByBoardNumber(boardNumber);

            // 좋아요 데이터 삭제 //
            favoriteRepository.deleteByBoardNumber(boardNumber); //! 어떻게 이게 딜리트작업을 수행하는건지

            // 데이터베이스에서 게시물 삭제 //
            boardRepository.delete(boardEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteBoardResponseDto.success();
    }
    
}
