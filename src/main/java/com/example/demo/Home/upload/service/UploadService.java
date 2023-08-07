package com.example.demo.Home.upload.service;

import com.example.demo.APIComponent.Flask.FlaskApi;
import com.example.demo.Home.upload.model.TextPictureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class UploadService {
    @Autowired
    private FlaskApi flaskApi;

    public String handleUploadAndProcess(
            String text1, String text2, String text3,
            MultipartFile picture1, MultipartFile picture2, MultipartFile picture3
    ) {
        try {
            // post 받은 데이터 저장
            TextPictureData textPictureData = setTextPictureData(text1, text2, text3, picture1, picture2, picture3);

            return printPictureData(textPictureData);
            //flask 서버에 전송하고 결과 return
            // return flaskApi.sendToFlaskServer(textPictureData);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private TextPictureData setTextPictureData(
            String text1, String text2, String text3,
            MultipartFile picture1, MultipartFile picture2, MultipartFile picture3
    ) throws IOException {
        TextPictureData textPictureData = new TextPictureData();

        textPictureData.setText1(text1);
        textPictureData.setText2(text2);
        textPictureData.setText3(text3);

        if (picture1 != null) {
            textPictureData.setPicture1(picture1.getBytes());
        }
        if (picture2 != null) {
            textPictureData.setPicture2(picture2.getBytes());
        }
        if (picture3 != null) {
            textPictureData.setPicture3(picture3.getBytes());
        }

        return textPictureData;
    }
    private String printPictureData(TextPictureData textPictureData) {
        StringBuilder result = new StringBuilder();

        result.append("Text 1 : ").append(textPictureData.getText1()).append("\n");
        result.append("Text 2 : ").append(textPictureData.getText2()).append("\n");
        result.append("Text 3 : ").append(textPictureData.getText3()).append("\n\n");

        result.append("Picture 1 : ").append(Arrays.toString(textPictureData.getPicture1())).append("\n");
        result.append("Picture 2 : ").append(Arrays.toString(textPictureData.getPicture2())).append("\n");
        result.append("Picture 3 : ").append(Arrays.toString(textPictureData.getPicture3())).append("\n");

        return result.toString();
    }
}
