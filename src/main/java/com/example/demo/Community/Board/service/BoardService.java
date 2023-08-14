package com.example.demo.Community.Board.service;

import com.example.demo.Community.Board.dto.BoardDTO;
import com.example.demo.Community.Board.model.Board;
import com.example.demo.Community.Board.repository.BoardRepository;
import com.example.demo.Community.Board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    private static final String NOT_FOUND_MESSAGE = "게시글을 찾을 수 없습니다.";
    private static final String NO_PERMISSION_MESSAGE = "권한이 없습니다.";
    public boolean checkUser(Authentication authentication){
        return userRepository.findByNickname(authentication.getName()) != null;
    }
    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setAuthor(board.getAuthor());
        boardDTO.setContent(board.getContent());
        boardDTO.setCreatedAt(board.getCreatedAt());

        return boardDTO;
    }
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
        return convertToDTO(board);
    }
    public boolean createBoard(BoardDTO boardDTO, Authentication authentication) {
        try {
            Board board = new Board();
            board.setAuthor(authentication.getName());
            board.setContent(boardDTO.getContent());
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("등록에 실패 했습니다. 에러내용 : " + e.getMessage());
        }
    }
    public boolean updateBoard(Long id, BoardDTO boardDTO, Authentication authentication) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));

        if (board.getAuthor().equals(authentication.getName())) {
            try {
                board.setContent(boardDTO.getContent());
                boardRepository.save(board);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("수정에 실패 했습니다. 에러내용: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException(NO_PERMISSION_MESSAGE);
        }
    }
    public boolean deleteBoard(Long id, Authentication authentication) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));

        if (board.getAuthor().equals(authentication.getName())) {
            try {
                boardRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("삭제에 실패 했습니다. 에러내용: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException(NO_PERMISSION_MESSAGE);
        }
    }
}

