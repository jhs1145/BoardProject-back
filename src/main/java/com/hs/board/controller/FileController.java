package com.hs.board.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.board.service.FileService;

import lombok.RequiredArgsConstructor;

// controller: 파일 컨트롤러 //
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
  
  private final FileService fileService;
  // API : 파일 업로드 메서드 //
  // request body는 전체를, param은 이메일 등 일부만 받아올수있음
  @PostMapping("/upload")
  public String upload(
    @RequestParam("file") MultipartFile file
  ) {
    String url = fileService.upload(file);
    return url;
  }

  // API : 이미지 불러오기 메서드 // 
  @GetMapping(value = "/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}) // produces : 어떤 타입으로 반환할건지
  public Resource getFile(
    @PathVariable(value = "fileName", required = true) String fileName
  ){
    Resource resource = fileService.getFile(fileName);
    return resource;
  }

}
