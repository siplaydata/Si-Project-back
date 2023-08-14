package com.example.demo.User.Join.service;

import com.example.demo.User.Join.dto.JoinDTO;
import com.example.demo.User.Join.model.JoinUser;
import com.example.demo.User.Join.repository.JoinRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private JoinRepository joinRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public boolean isIdUnique(String username) {
        return joinRepository.findByUsername(username) == null;
    }

    public boolean isNicknameUnique(String nickname) {
        return joinRepository.findByNickname(nickname) == null;
    }

    public boolean signUp(JoinDTO joinDTO) {
        JoinUser joinUser = new JoinUser();

        joinUser.setUsername(joinDTO.getUsername());
        joinUser.setPassword(encoder.encode(joinDTO.getPassword()));
        joinUser.setNickname(joinDTO.getNickname());
        joinUser.setName(joinDTO.getName());
        joinUser.setNumber(joinDTO.getNumber());
        joinUser.setEmail(joinDTO.getEmail());

        return joinRepository.save(joinUser) != null;
    }
}