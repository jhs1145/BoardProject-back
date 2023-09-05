package com.hs.board.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    // 파일 업로드 메소드 //
    String upload(MultipartFile file);
    // 이미지 불러오기 메소드 //
    Resource getFile(String fileName);

}
