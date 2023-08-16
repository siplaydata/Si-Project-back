package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.model.Board;
import com.example.cocktail.Commnity.Board.repository.BoardRepository;
import com.example.cocktail.Commnity.Userinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
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
        if (author.equals(nickname)) { throw new IllegalArgumentException("본인 외에는 할 수 없습니다."); }
    }
}
