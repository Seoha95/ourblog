package com.team.ourblog.controller;

import com.team.ourblog.dto.response.blog.ResBlogListDto;
import com.team.ourblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("list")
    public ResponseEntity<List<ResBlogListDto>> getBlogList(){
        List<ResBlogListDto> blogList = blogService.getBlogList();
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }



}
