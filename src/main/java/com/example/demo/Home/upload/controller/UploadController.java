package com.example.demo.Home.upload.controller;

//import com.example.demo.Home.model.TextPictureData;
import com.example.demo.Home.upload.model.TextPictureData;
import com.example.demo.Home.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping
    public String handleUploadAndProcess(
            @RequestParam(name = "text1", required = false, defaultValue = "") String text1,
            @RequestParam(name = "text2", required = false, defaultValue = "") String text2,
            @RequestParam(name = "text3", required = false, defaultValue = "") String text3,
            @RequestParam(name = "picture1", required = false) MultipartFile picture1,
            @RequestParam(name = "picture2", required = false) MultipartFile picture2,
            @RequestParam(name = "picture3", required = false) MultipartFile picture3) {

        try {
            return uploadService.handleUploadAndProcess(text1, text2, text3, picture1, picture2, picture3);
        } catch (Exception e) {
            return "업로드 및 처리 중 오류가 발생하였습니다. 에러 메시지 : " + e.getMessage();
        }
    }
}

