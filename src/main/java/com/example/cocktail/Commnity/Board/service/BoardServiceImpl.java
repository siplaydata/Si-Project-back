package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.dto.BoardDTO;
import com.example.cocktail.Commnity.Board.exception.BoardException;
import com.example.cocktail.Commnity.Board.model.Board;
import com.example.cocktail.Commnity.Board.repository.BoardRepository;
import com.example.cocktail.Commnity.Comment.dto.CommentDTO;
import com.example.cocktail.Commnity.Userinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardException boardException;
    @Override
    public boolean checkUser(String nickname) {
        return userRepository.findByNickname(nickname) != null;
    }
    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
    }
    @Override
    public void checkAuthorizationByUser(String author, String nickname) {
        if (!author.equals(nickname)) { boardException.throwIllegalArgumentException("본인 외에는 할 수 없습니다."); }
    }
    @Override
    public BoardDTO convertToDTO(Board board) {
        List<CommentDTO> commentDTOs = board.getComments().stream()
                .map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getUser().getNickname(), // 여기에서 User 객체의 nickname 을 가져옴
                        comment.getContent(),
                        comment.getCreatedAt()))
                .collect(Collectors.toList());

        return new BoardDTO(board.getId(), board.getAuthor(), board.getTitle(),
                board.getContent(), board.getCreatedAt(), commentDTOs);
    }
    @Override
    public List<BoardDTO> convertToDTOList(List<Board> foundBoards){
        return foundBoards.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void createBoardLimit(String nickname){
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startOfDay = currentDate.atStartOfDay();
        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);

        long numberOfBoardsToday = boardRepository.countByAuthorAndCreatedAtBetween(nickname, startOfDay, endOfDay);

        if (numberOfBoardsToday >= 10) { throw new RuntimeException("하루에 10개 이상의 게시글을 작성할 수 없습니다."); }
    }
}