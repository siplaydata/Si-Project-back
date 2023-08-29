package com.example.cocktail.User.Info.service;

import com.example.cocktail.User.Info.dto.InfoDTO;
import com.example.cocktail.User.Info.model.Info;
import com.example.cocktail.User.Info.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService{
    @Autowired
    private InfoRepository infoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public boolean checkUser(String nickName) {
        return infoRepository.findByNickname(nickName) != null;
    }
    private Info findUser(String nickname) {
        return infoRepository.findByNickname(nickname);
    }
    public boolean checkUserPassword(String nickname, String password) {
        if (encoder.matches(password, findUser(nickname).getPassword())) {
            return true;
        } else {
            throw new BadCredentialsException("비밀번호가 일치 하지 않습니다.");
        }
    }
    public InfoDTO getUserInfo(String nickname) {
        Info userinfo = findUser(nickname);

        if (userinfo != null) {
            return new InfoDTO(
                    userinfo.getUsername(),
                    userinfo.getPassword(),
                    userinfo.getNickname(),
                    userinfo.getName(),
                    userinfo.getNumber(),
                    userinfo.getEmail()
            );
        }
        return null;
    }

    public boolean updateUserInfo(String nickname, InfoDTO infoDTO) {
        Info userinfo = findUser(nickname);

        if (userinfo != null) {
            try{
                userinfo.setUsername(infoDTO.getUsername());
                userinfo.setPassword(encoder.encode(infoDTO.getPassword()));
                userinfo.setName(infoDTO.getName());
                userinfo.setNumber(infoDTO.getNumber());
                userinfo.setEmail(infoDTO.getEmail());
                return true;

            } catch (Exception e) {
                throw new RuntimeException("수정 실패 ! ");
            }
        } else {
            throw new RuntimeException("일치하는 유저가 없습니다. ");
        }
    }

    public boolean deleteUserInfo(String nickname, String password) {
        Info userinfo = findUser(nickname);

        if (userinfo != null ) {
            try {
                if (userinfo.getNickname().equals(nickname) && encoder.matches(password, userinfo.getPassword())){
                    infoRepository.delete(userinfo);
                } else {
                    throw new IllegalArgumentException("비밀번호가 일치 하지 않습니다!");
                }
            } catch (Exception e) {
                throw new RuntimeException("탈퇴에 실패 하셨습니다.");
            }
        }
        return false;
    }
}
