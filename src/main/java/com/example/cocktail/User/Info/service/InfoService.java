package com.example.cocktail.User.Info.service;

import com.example.cocktail.User.Info.dto.InfoDTO;

public interface InfoService {
    boolean checkUser(String nickname);
    boolean checkUserPassword(String nickname, String password);
    InfoDTO getUserInfo(String nickname);

    boolean updateUserInfo(String nickname, InfoDTO infoDTO);

    boolean deleteUserInfo(String nickname, String password);
}
