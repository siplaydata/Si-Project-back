package com.example.demo.Community.Board.service;

import com.example.demo.Community.Board.dto.BoardDTO;
import com.example.demo.Community.Board.model.Board;
import com.example.demo.Community.Board.repository.BoardRepository;
import com.example.demo.Community.Comment.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    private BoardDTO convertToDTO(Board board) {
        List<CommentDTO> commentDTOs = board.getComments().stream()
                .map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getUser().getNickname(), // 여기에서 User 객체의 nickname을 가져옴
                        comment.getContent(),
                        comment.getCreatedAt()))
                .collect(Collectors.toList());

        return new BoardDTO(board.getId(), board.getAuthor(), board.getTitle(),
                board.getContent(), board.getCreatedAt(), commentDTOs);
    }
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }
    public BoardDTO getBoardById(Long id) {
        return convertToDTO(boardService.findBoardById(id));
    }
}