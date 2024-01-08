package com.team.ourblog.service;

import com.team.ourblog.dto.response.blog.ResBlogListDto;
import com.team.ourblog.entity.Blog;
import com.team.ourblog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;

    public List<ResBlogListDto> getBlogList(){
        List<Blog> blogList = blogRepository.findAll();
        return blogList.stream()
                .map(ResBlogListDto::fromEntity)
                .collect(Collectors.toList());
    }
}
